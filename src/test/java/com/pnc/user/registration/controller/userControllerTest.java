package com.pnc.user.registration.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class userControllerTest {
    @Autowired
    UserController userController;

    @Test
    public void isValidPasswordStrengthTest() {
        String test1 = "1P#";
        String test2 = "1Password#";
        String test3 = "1password#";
        String test4 = "1password@";
        String test5 = "1@deecd";
        String test6 = "Password#";
        assertTrue(userController.isValidPassword(test2));
        assertFalse(userController.isValidPassword(test1));
        assertFalse(userController.isValidPassword(test3));
        assertFalse(userController.isValidPassword(test4));
        assertFalse(userController.isValidPassword(test5));
        assertFalse(userController.isValidPassword(test6));
    }

    @Test
    public void isValidInputTest() {
        assertTrue(userController.isValidInput("hello"));
        assertFalse(userController.isValidInput(""));
        assertFalse(userController.isValidInput("null"));
        assertFalse(userController.isValidInput(null));
    }

}
