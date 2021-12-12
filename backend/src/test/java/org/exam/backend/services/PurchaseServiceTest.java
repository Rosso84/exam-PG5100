package org.exam.backend.services;


import org.exam.backend.StubApplication;
import org.exam.backend.entities.Purchase;
import org.exam.backend.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PurchaseServiceTest extends ServiceTestBase{


    /**
     * This test class is not needed as we allready test most of it in USerServiceTest
     * //TODO: remove if codecoverage is no different.
     * */

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    private String email = "Rosso@Hotmail.com";
    private String name = "Rosso";
    private String midleName = "Melodi";
    private String surename = "Merandi";
    private String address = "someAdress 99";
    private String postalCode = "33rd street";
    private String password = "MyPassword";


    private User createValidUser(String email) {
        boolean createdUser = userService.createUser(email, name, midleName, surename, address, postalCode, password);
        User user = userService.getUser(email, false);
        return user;
    }

    private Long createValidItem(String category, String title, String descr){

        Long itemID = itemService.createItem(category, title, descr);
        return itemID;
    }

    @Test
    public void testBookTrip(){

        User user1 = createValidUser( email );
        Long itemTourId = createValidItem("Tour", "Some title ", "Some description about the Item");
        assertNotNull( itemTourId );

        Long purchase_id = purchaseService.bookTrip( user1.getEmail(), itemTourId );
        Purchase bookedTrip = purchaseService.getBookedTrip( purchase_id );

        assertNotNull(purchase_id);
        assertEquals(user1.getEmail(), bookedTrip.getUser().getEmail() );
    }


    @Test
    public void testBookMany(){

        User user = createValidUser( email );

        Long itemTourId = createValidItem("Tour", "Some title ", "Some description about the Item");
        Long itemTourId1 = createValidItem("Tour", "Some title2 ", "Some description about the Item");
        Long itemTourId2 = createValidItem("Tour", "Some title3 ", "Some description about the Item");
        Long itemTourId3 = createValidItem("Tour", "Some title4 ", "Some description about the Item");

        assertNotNull( itemTourId );
        assertNotNull( itemTourId1 );
        assertNotNull( itemTourId2 );
        assertNotNull( itemTourId3 );

        Long purchase_id = purchaseService.bookTrip( user.getEmail(), itemTourId );
        Long purchase_id1 = purchaseService.bookTrip( user.getEmail(), itemTourId1 );
        Long purchase_id2 = purchaseService.bookTrip( user.getEmail(), itemTourId2 );
        Long purchase_id3 = purchaseService.bookTrip( user.getEmail(), itemTourId3 );

        Purchase bookedTrip = purchaseService.getBookedTrip( purchase_id );
        Purchase bookedTrip1 = purchaseService.getBookedTrip( purchase_id1 );
        Purchase bookedTrip2 = purchaseService.getBookedTrip( purchase_id2 );
        Purchase bookedTrip3 = purchaseService.getBookedTrip( purchase_id3 );

        assertNotNull( purchase_id );
        assertNotNull( purchase_id1 );
        assertNotNull( purchase_id2 );
        assertNotNull( purchase_id3 );
        assertEquals( user.getEmail(), bookedTrip.getUser().getEmail() );
        assertEquals( user.getEmail(), bookedTrip1.getUser().getEmail() );
        assertEquals( user.getEmail(), bookedTrip2.getUser().getEmail() );
        assertEquals( user.getEmail(), bookedTrip3.getUser().getEmail() );
    }


}

