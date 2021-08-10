package org.exam.backend.services;


import org.exam.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService {


    @Autowired
    private EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public boolean createUser(String email, String firstName, String midleName, String sureName,
                              String address, String postalCode, String password) {

        String hashedPassword = passwordEncoder.encode(password);

        if (em.find(User.class, email) != null) {
            return false;
        }

        User user = new User();
        user.setEmail(email);
        user.setFirstname(firstName);
        user.setMiddleName(midleName);
        user.setSurname(sureName);
        user.setAddress(address);
        user.setPostalCode(postalCode);
        user.setPassword(hashedPassword);
        user.setNumberOfVotes(0);

        user.setRoles(Collections.singleton("USER"));
        user.setEnabled(true);

        em.persist(user);

        return true;
    }


    public User getUser(String email_id){
        User user = em.find(User.class, email_id);

        return user;
    }


    public List<User> getAllUsers(){
        TypedQuery<User> query = em.createQuery("select c from User c", User.class);
        List<User> users = query.getResultList();

      /*  if(with_purchases){
            //force loading
            users.forEach(c -> c.getPurchases().size());
        }*/

        return users;
    }


}

