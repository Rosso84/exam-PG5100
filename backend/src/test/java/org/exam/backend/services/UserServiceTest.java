package org.exam.backend.services;

import org.exam.backend.StubApplication;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest extends ServiceTestBase{



    @Autowired
    private UserService userService;


    private ValidatorFactory valFactory;
    private Validator validator;

    private String email = "Foo@bar2.com";
    private String name = "Foo";
    private String midleName = "Melodi";
    private String surename = "Merandi";
    private String address = "someAdress 99";
    private String postalCode = "33rd street";
    private String password = "MyPassword123";

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


    public User createValidUser(String email, String password){
        User user = new User();
        user.setEmail(email);
        user.setFirstname(name);
        user.setMiddleName(midleName);
        user.setSurname(surename);
        user.setAddress(address);
        user.setPostalCode(postalCode);
        user.setPassword(password);
        user.setEnabled(true);

        return user;
    }

    @Test
    public void testNoUsers() {
        List<User> list = userService.getAllUsers(false);
        assertEquals(0, list.size());
    }


    @Test
    public void testCreateUser() {

        boolean created = userService.createUser(email, name, midleName, surename, address, postalCode, password);
        assertTrue(created);

        User user = userService.getUser( email, false );
        assertEquals( email, user.getEmail() );

    }


    @Test
    public void testCreateTwiceFails(){
        boolean created = userService.createUser(email, name, midleName, surename, address, postalCode, password);
        assertTrue( created );

        boolean reCreated = userService.createUser(email, name, midleName, surename, address, postalCode, password);
        assertFalse( reCreated );
    }


    @Test
    public void testTooShortEmail(){
        String myEMail = "x@f.n";

        User user = createValidUser(myEMail, password);

        assertTrue( hasViolations( user ) );
    }


    @Test
    public void testTooLongEmail(){

        String x = "";
        for(int i = 0; i < 300; i++){ //max is set to 250 char
            x = x.concat("a");
        }

        String email = "Rossi";
        email = email.concat( x );
        email = email.concat("@Gmail.com");

        User user = createValidUser(email, password);

        assertTrue( hasViolations( user ) );
    }



    @Test
    public void testHashedPasswordTooLong(){

        /**
         * Note the password will be hashed after encryption and returns a larger String of mixed values,
         * so this does not test how many characters a user inputs but rather how big the hashed password is.
         * Limit of length will be set in frontend*/

        String hashedPassword = "";

        for(int i = 0; i < 300; i++){ //max is set to 250 char
            hashedPassword = hashedPassword.concat("a"); //note using concat instead of ´+´ for string concatenation
        }

        User user2 = createValidUser( email, hashedPassword );
        assertTrue( hasViolations( user2 ) );

    }



}
