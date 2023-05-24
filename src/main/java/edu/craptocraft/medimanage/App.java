package edu.craptocraft.medimanage;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataIntegrityViolationException;

import edu.craptocraft.medimanage.entity.Doctors;
import edu.craptocraft.medimanage.entity.Medicines;
import edu.craptocraft.medimanage.entity.Patients;
import edu.craptocraft.medimanage.entity.Prescriptions;
import edu.craptocraft.medimanage.service.DoctorsService;
import edu.craptocraft.medimanage.service.MedicinesService;
import edu.craptocraft.medimanage.service.PatientsService;
import edu.craptocraft.medimanage.service.PrescriptionsService;

@SpringBootApplication
public class App implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private PrescriptionsService servicePrescription;
    
    @Autowired
    private DoctorsService serviceDoctor;
    
    @Autowired
    private MedicinesService serviceMedicine;
    
    @Autowired
    private PatientsService servicePatient;

    @Override
    public void run(String... args) throws Exception {
        try {
            Doctors doctor1 = new Doctors("arodriguez@gmail.com", "password", "Alejandro Rodríguez");
            Doctors doctor2 = new Doctors("jpiedra@gmail.com", "password", "José Piedra");
            Doctors doctor3 = new Doctors("flawrence@gmail.com", "password", "Fernando Lawrence");
            serviceDoctor.create(doctor1);
            serviceDoctor.create(doctor2);
            serviceDoctor.create(doctor3);

            Medicines medicine1 = new Medicines("Alinex", 10.0f, 5.0f);
            Medicines medicine2 = new Medicines("Lenix", 15.0f, 2.0f);
            Medicines medicine3 = new Medicines("Preditinol", 20.0f, 3.0f);
            serviceMedicine.create(medicine1);
            serviceMedicine.create(medicine2);
            serviceMedicine.create(medicine3);

            Patients patient1 = new Patients("aklutznovich@gmail.com", "Ajax Klutznovich");
            Patients patient2 = new Patients("dragnvindr@gmail.com", "Diluc Ragnvindr");
            Patients patient3 = new Patients("kalberich@gmail.com", "Kaeya Alberich");
            servicePatient.create(patient1);
            servicePatient.create(patient2);
            servicePatient.create(patient3);
            
            Prescriptions prescriptions1 = new Prescriptions(new Doctors(1), new Medicines(1), new Patients(1), LocalDate.of(2023, 05, 15));
            Prescriptions prescriptions2 = new Prescriptions(new Doctors(2), new Medicines(2), new Patients(2), LocalDate.of(2023, 06, 12));
            Prescriptions prescriptions3 = new Prescriptions(new Doctors(3), new Medicines(3), new Patients(3), LocalDate.of(2023, 01, 18));
            servicePrescription.create(prescriptions1);
            servicePrescription.create(prescriptions2);
            servicePrescription.create(prescriptions3);
        } catch (DataIntegrityViolationException e) {
            // Initial data has already been created.
            // Do nothing.
        }
    }

}
