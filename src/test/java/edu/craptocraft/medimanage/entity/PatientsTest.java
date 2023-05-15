package edu.craptocraft.medimanage.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class PatientsTest {

    @Test
    public void testGetId() {
        Patients patient = new Patients();
        patient.setId(1);
        int id = patient.getId();
        assertEquals(1, id);
    }

    @Test
    public void testGetEmail() {
        Patients patient = new Patients();
        patient.setEmail("patient@example.com");
        String email = patient.getEmail();
        assertEquals("patient@example.com", email);
    }

    @Test
    public void testGetName() {
        Patients patient = new Patients();
        patient.setName("John Doe");
        String name = patient.getName();
        assertEquals("John Doe", name);
    }

    @Test
    public void testEquals() {
        Patients patient1 = new Patients(1);
        Patients patient2 = new Patients(1);
        Patients patient3 = new Patients(2);

        assertTrue(patient1.equals(patient2));
        assertFalse(patient1.equals(patient3));
    }

    @Test
    public void testHashCode() {
        Patients patient1 = new Patients(1);
        Patients patient2 = new Patients(1);

        assertEquals(patient1.hashCode(), patient2.hashCode());
    }
}
