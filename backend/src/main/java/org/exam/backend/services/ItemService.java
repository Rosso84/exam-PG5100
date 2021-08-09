package org.exam.backend.services;

import org.exam.backend.entities.Item;
import org.exam.backend.entities.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemService {

    @Autowired
    EntityManager em;

    public Long createItem(String category,String title, String description){

        Item item = new Item();
        item.setCategory( category );
        item.setTitle( title );
        item.setDescription( description );
        em.persist( item );

        return item.getId();
    }

    public Item getItem(Long id, boolean with_rankings){
        Item item = em.find(Item.class, id);

        if ( with_rankings && item != null){
            item.getRankings().size();
        }

        return item;
    }


    public List<Item> getALLItemOrderByCategory(boolean with_rankings){
        TypedQuery<Item> query = em.createQuery("select i from Item i order by i.category", Item.class);
        List<Item> items = query.getResultList();

        if ( with_rankings ){
            items.forEach(i -> i.getRankings().size());
        }

        return items;
    }

    public Double getAverageRank(Long item_id){

        Item item = getItem(item_id, true);

        List<Rank> rankings = item.getRankings();

        List<Integer> scores = rankings.stream()
                .map(Rank::getScore)
                .collect( Collectors.toList() );

        return scores.stream()
                .mapToDouble(s -> s)
                .average()
                .orElse(0.0);
    }




}
