package edu.craptocraft.medimanage.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MedicinesTest {

    @Test
    public void testGetId() {
        Medicines medicine = new Medicines();
        medicine.setId(1);
        int id = medicine.getId();
        assertEquals(1, id);
    }

    @Test
    public void testGetName() {
        Medicines medicine = new Medicines();
        medicine.setName("Paracetamol");
        String name = medicine.getName();
        assertEquals("Paracetamol", name);
    }

    @Test
    public void testGetTmax() {
        Medicines medicine = new Medicines();
        medicine.setTmax(10.5f);
        float tmax = medicine.getTmax();
        assertEquals(10.5f, tmax, 0.001);
    }

    @Test
    public void testGetTmin() {
        Medicines medicine = new Medicines();
        medicine.setTmin(5.2f);
        float tmin = medicine.getTmin();
        assertEquals(5.2f, tmin, 0.001);
    }

    @Test
    public void testEquals() {
        Medicines medicine1 = new Medicines(1);
        Medicines medicine2 = new Medicines(1);
        Medicines medicine3 = new Medicines(2);

        assertTrue(medicine1.equals(medicine2));
        assertFalse(medicine1.equals(medicine3));
    }

    @Test
    public void testHashCode() {
        Medicines medicine1 = new Medicines(1);
        Medicines medicine2 = new Medicines(1);

        assertEquals(medicine1.hashCode(), medicine2.hashCode());
    }
}
