package edu.craptocraft.medimanage.entity;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DoctorsTest {

    @Test
    public void testGetId() {
        Doctors doctor = new Doctors();
        doctor.setId(1);
        int id = doctor.getId();
        assertEquals(1, id);
    }

    @Test
    public void testGetEmail() {
        Doctors doctor = new Doctors();
        doctor.setEmail("doctor@example.com");
        String email = doctor.getEmail();
        assertEquals("doctor@example.com", email);
    }

    @Test
    public void testGetPassword() {
        Doctors doctor = new Doctors();
        doctor.setPassword("password123");
        String password = doctor.getPassword();
        assertEquals("password123", password);
    }

    @Test
    public void testGetName() {
        Doctors doctor = new Doctors();
        doctor.setName("Dr. John Doe");
        String name = doctor.getName();
        assertEquals("Dr. John Doe", name);
    }

    @Test
    public void testGetLastLog() {
        Doctors doctor = new Doctors();
        LocalDate lastLog = LocalDate.now();
        doctor.setLastLog(lastLog);
        LocalDate retrievedLastLog = doctor.getLastLog();
        assertEquals(lastLog, retrievedLastLog);
    }

    @Test
    public void testGetSession() {
        Doctors doctor = new Doctors();
        doctor.setSession(3);
        Integer session = doctor.getSession();
        assertEquals(3, session, 0);
    }

    @Test
    public void testEquals() {
        Doctors doctor1 = new Doctors(1);
        Doctors doctor2 = new Doctors(1);
        Doctors doctor3 = new Doctors(2);

        assertTrue(doctor1.equals(doctor2));
        assertFalse(doctor1.equals(doctor3));
    }

    @Test
    public void testHashCode() {
        Doctors doctor1 = new Doctors(1);
        Doctors doctor2 = new Doctors(1);

        assertEquals(doctor1.hashCode(), doctor2.hashCode());
    }
}
