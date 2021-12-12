package org.exam.frontend.controllers;


import org.exam.backend.entities.Purchase;
import org.exam.backend.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ItemController ic;



    public String bookTrip(String userId_email) {

        Long purchaseId = purchaseService.bookTrip( userId_email, ic.getSelectedItem().getId() );

        ic.setSelectedItem( null );

        return "/receipt.xhtml&faces-redirect=true";
    }

    public Purchase getBookedTrip(long purchaseId) {
        return purchaseService.getBookedTrip( purchaseId );
    }


}
