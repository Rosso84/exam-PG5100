package org.exam.backend.services;

import org.exam.backend.StubApplication;
import org.exam.backend.entities.Item;
import org.exam.backend.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ItemServiceTest extends ServiceTestBase{


    @Autowired
    private ItemService itemService;

    @Autowired
    private RankService rankService;

    @Autowired
    private UserService userService;


    private Long createValidItem(String category, String title, String descr){

        Long itemID = itemService.createItem(category, title, descr);
        return itemID;
    }


    public User createValidUser( String email ){
        boolean created = userService.createUser(
                email,
                "name1",
                "mm",
                "surename",
                "address",
                "2020",
                "123456");
        assertTrue(created);

        return userService.getUser( email );
    }


    private void createMultipleItems(){
        String ctg1 = "Tour";
        String ctg2 = "Watersport";

        String title1 = "City tour";
        String title2 = "Multiple tour";
        String title3 = "Wine tour2";

        String title4 = "swimming2";
        String title5 = "swimming3";
        String title6 = "swimming4";
        String title7 = "swimming5";

        String description = "Description about the Item. It is a nice tour. Good Service and nice people, very popular";

        Long tour1 = createValidItem( ctg1,title1, description );
        Long tour2 = createValidItem( ctg1, title2, description );
        Long tour3 = createValidItem( ctg1, title3, description );

        Long water1 = createValidItem( ctg2, title4, description );
        Long water2 = createValidItem( ctg2, title5, description );
        Long water3 = createValidItem( ctg2, title6, description );
        Long water4 = createValidItem( ctg2, title7, description );
    }


    @Test
    public void testNoItems(){
        List<Item> trips = itemService.getALLItemOrderByCategory(false);
        assertEquals(0, trips.size());
    }


    @Test
    public void createItem(){
        Long ItemId = createValidItem(
                "Tour",
                "SKi Tour",
                "Description about the trip. It is a nice trip. Good Service, very popular");
        assertNotNull( ItemId );
    }

    @Test
    public void getALLItemOrderByCategory() {
        createMultipleItems();

        List<Item> items = itemService.getALLItemOrderByCategory(false);

        assertEquals(7, items.size() );
    }

    @Test
    public void getAllItemsByCategory(){
        String ctg1 = "Tour";
        String ctg2 = "Watersport";

        createMultipleItems();

        List<Item> itemsTours = itemService.getAllItemsByCategory( ctg1, true);
        List<Item> itemsWaterSport = itemService.getAllItemsByCategory( ctg2, true);

        assertEquals(3, itemsTours.size());
        assertEquals(4, itemsWaterSport.size());

        assertEquals(itemsTours.get(0).getCategory(), ctg1);
        assertEquals(itemsTours.get(1).getCategory(), ctg1);
        assertEquals(itemsTours.get(2).getCategory(), ctg1);

        assertEquals(itemsWaterSport.get(0).getCategory(), ctg2);
        assertEquals(itemsWaterSport.get(1).getCategory(), ctg2);
        assertEquals(itemsWaterSport.get(2).getCategory(), ctg2);
        assertEquals(itemsWaterSport.get(3).getCategory(), ctg2);


    }


    @Test
    public void updateComment() {

        User user = createValidUser("roozm@hotmail.com");
        assertNotNull (user);
        String userID = user.getEmail();

        String comment = "The place was terrific!!!";
        String category = "Category";
        String title = "Title";
        String description = "description";

        Long itemId = createValidItem( category, title, description);
        assertNotNull( itemId );

        Long rankId = rankService.rankItem(userID, itemId, 5, comment);
        assertNotNull( rankId );

        String newComment = "The location was really hard to find";

        //Update
        Long updatedRankId = itemService.updateComment(itemId, userID, newComment);
        assertNotNull( updatedRankId );

        Item item = itemService.getItem( updatedRankId, true);

        assertEquals(1, item.getRankings().size() );

        //Verify
        assertEquals( newComment, item.getRankings().get( 0 ).getComment() );
    }


    @Test
    void updateScore() {
        User user = createValidUser("roozm@hotmail.com");
        assertNotNull (user);
        String userID = user.getEmail();


        String category = "Category";
        String title = "Title";
        String description = "description";
        String comment = "The place was terrific!!!";

        Integer score = 3;

        Long itemId = createValidItem( category, title, description);
        assertNotNull( itemId );

        Long rankId = rankService.rankItem(userID, itemId, score, comment);
        assertNotNull( rankId );

        Integer newScore = 5;
        //Update
        Long updatedRankId = itemService.updateScore(itemId, userID, newScore);
        assertNotNull( updatedRankId );

        Item item = itemService.getItem( updatedRankId, true);

        assertEquals(1, item.getRankings().size() );

        //Verify
        assertEquals( newScore, item.getRankings().get( 0 ).getScore() );
    }



    @Test
    void getItemsAverageRank() {
        Long itemId = createValidItem("Skiing", "North Pole", "Dangerous and extremely cold");

        User user1 = createValidUser("Foo@bar.com");
        User user2 = createValidUser("Foo2@bar2.com");
        User user3 = createValidUser("Foo3@bar3.com");
        User user4 = createValidUser("Foo4@bar4.com");
        User user5 = createValidUser("Foo5@bar5.com");


        Long rankId1 = rankService.rankItem(user1.getEmail(),itemId, 4,"commment1");
        Long rankId2 = rankService.rankItem(user2.getEmail(),itemId, 3,"commment1");
        Long rankId3 = rankService.rankItem(user3.getEmail(),itemId, 2,"commment1");
        Long rankId4 = rankService.rankItem(user4.getEmail(),itemId, 3,"commment1");
        Long rankId5 = rankService.rankItem(user5.getEmail(),itemId, 5,"commment1");

        Double avg = itemService.getItemsAverageRank(itemId);

        assertEquals(3.4, avg);

    }



}
