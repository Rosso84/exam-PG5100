package org.exam.backend.services;

import org.exam.backend.StubApplication;
import org.exam.backend.entities.Item;
import org.exam.backend.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ItemServiceTest extends ServiceTestBase{


    @Autowired
    private ItemService itemService;

    private ValidatorFactory valFactory;
    private Validator validator;

    LocalDate dateOfToday = LocalDate.now();


    @BeforeEach
    public void init() {
        valFactory = Validation.buildDefaultValidatorFactory();
        validator = valFactory.getValidator();
    }

    private <T> boolean hasViolations(T obj){
        Set<ConstraintViolation<T>> violations = validator.validate(obj);

        for(ConstraintViolation<T> cv : violations){
            System.out.println("VIOLATION: "+cv.toString());
        }

        return violations.size() > 0;
    }

    private Long createValidItem(String category, String title, String descr){

        Long itemID = itemService.createItem(category, title, descr);
        return itemID;
    }

    private void createMultipleItems(){
        String ctg1 = "Tour";
        String ctg2 = "Food And Drink";
        String ctg3 = "Attractions";

        String title1 = "City tour";
        String title2 = "Multiple tour";
        String title3 = "Wine tour";
        String title4 = "Food Tour";

        String description = "Description about the Item. It is a nice tour. Good Service and nice people, very popular";

        Long tour1 = createValidItem( ctg1,title1, description );
        Long tour2 = createValidItem( ctg1, title2, description );

        Long foodAndDrink1 = createValidItem( ctg2, title3, description );
        Long foodAndDrink2 = createValidItem( ctg2, title4, description );
    }


    @Test
    public void testNoItems(){
        List<Item> trips = itemService.getALLItemOrderByCategory(false);
        assertEquals(0, trips.size());
    }


    @Test
    public void testCreateItem(){
        Long ItemId = createValidItem(
                "Tour",
                "SKi Tour",
                "Description about the trip. It is a nice trip. Good Service, very popular");
        assertNotNull( ItemId );
    }




    @Test
    public void testAverageScorePerItem(){
        User user = new User();
        user.setEmail("email@mail.no");
        user.setFirstname("Foo");
        user.setMiddleName("none");
        user.setSurname("Bar");
        user.setAddress("address");
        user.setPostalCode("2020");
        user.setPassword("123456");
        user.setEnabled(true);

        Long item = createValidItem(
                "Tour",
                "Ski Tour",
                "Description about the trip. It is a nice trip. Good Service, very popular");

        //TODO: finish this test
    }







}
