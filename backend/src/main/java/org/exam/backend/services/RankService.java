package org.exam.backend.services;

import org.exam.backend.entities.Item;
import org.exam.backend.entities.Rank;
import org.exam.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class RankService {

    @Autowired
    EntityManager em;

    public Long rankItem(String userId, Long itemId, Integer score, String comment){
        User user = em.find(User.class, userId);
        Item item = em.find(Item.class, itemId);

        if (user == null) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist");
        }
        if (item == null) {
            throw new IllegalArgumentException("Item with id " + itemId + " does not exist");
        }

        if ( userHasRankedItem( userId, itemId )){
            System.out.println( "User with id " + userId + " has already ranked item with id " + itemId);
            return null;
        }

        LocalDate todaysDate = LocalDate.now();

        Rank rank = new Rank();
        rank.setUser( user );
        rank.setItem( item );
        rank.setScore( score );
        rank.setComment( comment );
        rank.setDateCommented( todaysDate );
        em.persist( rank );

        Integer increment = user.getNumberOfVotes() + 1;
        user.setNumberOfVotes( increment );

        item.getRankings().add( rank );

        return rank.getId();
    }


    public Rank getRankedItem(Long id){
        return em.find(Rank.class, id);
    }


    public boolean userHasRankedItem(String userId, Long itemId){

        TypedQuery<Rank> query = em.createQuery(
                "select r from Rank r where r.user.email = ?1 and r.item.id = ?2",
                Rank.class);
        query.setParameter(1, userId);
        query.setParameter(2, itemId);

        return query.getResultList().size() > 0;
    }






}
