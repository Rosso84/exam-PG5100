package org.exam.frontend.controllers;


import org.exam.backend.entities.Purchase;
import org.exam.backend.entities.User;
import org.exam.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserInfoController implements Serializable {


    @Autowired
    private UserService userService;

    private User user;


    public String getUserName(){
        return ( (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getUsername();
    }

    public User getUserInfoWithoutPurchases(){
        this.user = userService.getUser( getUserName(), false);
        return user;
    }

    public User getUserInfoWithPurchases(){
        this.user = userService.getUser( getUserName(), true);
        return user;
    }


   public boolean isPurchasesEmpty(User user){
        return user.getPurchases().size() > 0;
    }

    public boolean isTripPurchased(User user, Long tripId){

        List<Purchase> purchasedTrips = user.getPurchases();

        for(Purchase p: purchasedTrips){
            if (p.getItem().getId().equals( tripId ) ){
                return true;
            }
        }
        return false;
    }


}

