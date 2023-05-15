package edu.craptocraft.medimanage.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.time.LocalDate;

public class PrescriptionsTest {

    @Test
    public void testGetId() {
        Prescriptions prescription = new Prescriptions();
        prescription.setId(1);
        int id = prescription.getId();
        assertEquals(1, id);
    }

    @Test
    public void testGetIdDoctor() {
        Doctors doctor = new Doctors(1);
        Prescriptions prescription = new Prescriptions();
        prescription.setIdDoctor(doctor);
        Doctors retrievedDoctor = prescription.getIdDoctor();
        assertEquals(doctor, retrievedDoctor);
    }

    @Test
    public void testGetIdMedicine() {
        Medicines medicine = new Medicines(1);
        Prescriptions prescription = new Prescriptions();
        prescription.setIdMedicine(medicine);
        Medicines retrievedMedicine = prescription.getIdMedicine();
        assertEquals(medicine, retrievedMedicine);
    }

    @Test
    public void testGetIdPatient() {
        Patients patient = new Patients(1);
        Prescriptions prescription = new Prescriptions();
        prescription.setIdPatient(patient);
        Patients retrievedPatient = prescription.getIdPatient();
        assertEquals(patient, retrievedPatient);
    }

    @Test
    public void testGetDate() {
        LocalDate date = LocalDate.now();
        Prescriptions prescription = new Prescriptions();
        prescription.setDate(date);
        LocalDate retrievedDate = prescription.getDate();
        assertEquals(date, retrievedDate);
    }

    @Test
    public void testEquals() {
        Prescriptions prescription1 = new Prescriptions(1);
        Prescriptions prescription2 = new Prescriptions(1);
        Prescriptions prescription3 = new Prescriptions(2);

        assertTrue(prescription1.equals(prescription2));
        assertFalse(prescription1.equals(prescription3));
    }

    @Test
    public void testHashCode() {
        Prescriptions prescription1 = new Prescriptions(1);
        Prescriptions prescription2 = new Prescriptions(1);

        assertEquals(prescription1.hashCode(), prescription2.hashCode());
    }
}
