package org.exam.backend.services;

import org.exam.backend.StubApplication;
import org.exam.backend.entities.Rank;
import org.exam.backend.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RankServiceTest extends ServiceTestBase{


    @Autowired
    private RankService rankService;

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
        User user = userService.getUser(email);
        return user;
    }

    private Long createValidItem(String category, String title, String descr){

        Long itemID = itemService.createItem(category, title, descr);
        return itemID;
    }


    @Test
    public void testRankItem(){
        Long itemTourId = createValidItem("Tour", "Some title ", "Some description about the Item");
        User user1 = createValidUser( email );

        String usersMail = user1.getEmail();

        Long rankId = rankService.rankItem( usersMail, itemTourId, 1,"Some comment about the Item.");
        assertNotNull( rankId );

        Rank rankedItem = rankService.getRankedItem( rankId );

        assertEquals( rankId, rankedItem.getId() );
        assertEquals( user1.getEmail(), rankedItem.getUser().getEmail() );

    }

    @Test
    public void testRankSameItemTwiceFails(){
        Long itemTourId = createValidItem("Tour", "Some title ", "Some description about the Item");
        // Long itemWineId = createValidItem("Wine And Food", "Some title ", "Some description about the Item");

        User user1 = createValidUser( email );
       /* User user2 = createValidUser("foo@bar.com");
        User user3 = createValidUser("foo2@bar3.com");
*/

        String usersMail = user1.getEmail();

        Integer incrementVotes = user1.getNumberOfVotes() + 1;

        Long rankId = rankService.rankItem( usersMail, itemTourId, 4,"Some comment about the Item.");
        user1.setNumberOfVotes( incrementVotes );
        assertNotNull( rankId );

        Rank rankedItem = rankService.getRankedItem( rankId );

        assertEquals( rankId, rankedItem.getId() );
        assertEquals( user1.getEmail(), rankedItem.getUser().getEmail() );

    }






}
