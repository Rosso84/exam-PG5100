package org.exam.backend.services;

import org.exam.backend.entities.Item;
import org.exam.backend.entities.Rank;
import org.exam.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;

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

        LocalDate todaysDate = LocalDate.now();

        Rank rank = new Rank();
        rank.setUser( user );
        rank.setItem( item );
        rank.setScore( score );
        rank.setComment( comment );
        rank.setDateCommented( todaysDate );

        return rank.getId();
    }

    public Rank getRankedItem(Long id){
        return em.find(Rank.class, id);
    }


}
