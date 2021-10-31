package org.exam.backend.services;

/*This class has been copied from Andrea Arcuris repository:
* https://github.com/arcuri82/testing_security_development_enterprise_systems/tree/master/intro/exercise-solutions/quiz-game/part-11*/


import org.exam.backend.entities.Item;
import org.exam.backend.entities.Purchase;
import org.exam.backend.entities.Rank;
import org.exam.backend.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/*This class has been copied from Andrea Arcuris repository:
 * https://github.com/arcuri82/testing_security_development_enterprise_systems/tree/master/intro/exercise-solutions/quiz-game/part-11*/


@Service
@Transactional
public class ResetService {

    @PersistenceContext
    private EntityManager em;

    public void resetDatabase(){
        //Have to use native SQL for ElementCollection
        Query query = em.createNativeQuery("delete from user_roles");
        query.executeUpdate();

        deleteEntities(Rank.class);
        deleteEntities(Purchase.class);
        deleteEntities(Item.class);
        deleteEntities(User.class);

    }

    private void deleteEntities(Class<?> entity){

        if(entity == null || entity.getAnnotation(Entity.class) == null){
            throw new IllegalArgumentException("Invalid non-entity class");
        }

        String name = entity.getSimpleName();

        Query query = em.createQuery("delete from " + name);
        query.executeUpdate();
    }

}

