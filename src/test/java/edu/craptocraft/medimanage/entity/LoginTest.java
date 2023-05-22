package edu.craptocraft.medimanage.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LoginTest {

    private Login login;

    @Before
    public void setup() {
        login = new Login("test@example.com", "password123");
    }

    @Test
    public void testGetEmail() {
        String email = login.getEmail();
        assertEquals("test@example.com", email);
    }

    @Test
    public void testSetEmail() {
        login.setEmail("new@example.com");
        String email = login.getEmail();
        assertEquals("new@example.com", email);
    }

    @Test
    public void testGetPassword() {
        String password = login.getPassword();
        assertEquals("password123", password);
    }

    @Test
    public void testSetPassword() {
        login.setPassword("newpassword456");
        String password = login.getPassword();
        assertEquals("newpassword456", password);
    }

}
