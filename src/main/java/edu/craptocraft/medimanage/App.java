package edu.craptocraft.medimanage;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.craptocraft.medimanage.entity.Doctors;
import edu.craptocraft.medimanage.entity.Medicines;
import edu.craptocraft.medimanage.entity.Patients;
import edu.craptocraft.medimanage.repository.DoctorsRepo;
import edu.craptocraft.medimanage.repository.MedicinesRepo;
import edu.craptocraft.medimanage.repository.PatientsRepo;

@SpringBootApplication
public class App implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private DoctorsRepo repoDoctors;

    @Autowired
    private MedicinesRepo repoMedicines;

    @Autowired
    private PatientsRepo repoPatients;

    @Override
    public void run(String... args) throws Exception {
        Doctors doctor1 = new Doctors("arodriguez@gmail.com", "password", "Alejandro Rodríguez", LocalDate.of(2023, 05, 15), 1);
        Doctors doctor2 = new Doctors("jpiedra@gmail.com", "password", "José Piedra", LocalDate.of(2023, 05, 15), 2);
        Doctors doctor3 = new Doctors("flawrence@gmail.com", "password", "Fernando Lawrence", LocalDate.of(2023, 05, 15), 3);
        repoDoctors.save(doctor1);
        repoDoctors.save(doctor2);
        repoDoctors.save(doctor3);

        Medicines medicine1 = new Medicines("Alinex", 10.0f, 5.0f);
        Medicines medicine2 = new Medicines("Lenix", 15.0f, 2.0f);
        Medicines medicine3 = new Medicines("Preditinol", 20.0f, 3.0f);
        repoMedicines.save(medicine1);
        repoMedicines.save(medicine2);
        repoMedicines.save(medicine3);

        Patients patient1 = new Patients("aklutznovich@gmail.com", "Ajax Klutznovich");
        Patients patient2 = new Patients("dragnvindr@gmail.com", "Diluc Ragnvindr");
        Patients patient3 = new Patients("kalberich@gmail.com", "Kaeya Alberich");
        repoPatients.save(patient1);
        repoPatients.save(patient2);
        repoPatients.save(patient3);
    }

}
