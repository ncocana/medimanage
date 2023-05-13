package edu.craptocraft.medimanage.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.craptocraft.medimanage.entity.Doctors;
import edu.craptocraft.medimanage.entity.Medicines;
import edu.craptocraft.medimanage.entity.Patients;
import edu.craptocraft.medimanage.entity.Prescriptions;
import edu.craptocraft.medimanage.repository.DoctorsRepo;
import edu.craptocraft.medimanage.repository.MedicinesRepo;
import edu.craptocraft.medimanage.repository.PatientsRepo;
import edu.craptocraft.medimanage.repository.PrescriptionsRepo;
import edu.craptocraft.medimanage.service.PrescriptionsService;

@Service
public class PrescriptionsIMPL implements PrescriptionsService {

    @Autowired
    private PrescriptionsRepo repo;
    
    private static PatientsRepo repoPatients;
    private static DoctorsRepo repoDoctors;
    private static MedicinesRepo repoMedicines;

    @Override
    public Prescriptions create(Prescriptions prescription) {
        prescription.setId(prescription.getId());
        prescription.setIdDoctor(prescription.getIdDoctor());
        prescription.setIdMedicine(prescription.getIdMedicine());
        prescription.setIdPatient(prescription.getIdPatient());
        prescription.setDate(prescription.getDate());
        return this.repo.save(prescription);
    }

    @Override
    public List<Prescriptions> getAll() {
        return (List<Prescriptions>) this.repo.findAll();
    }

    @Override
    public Prescriptions getOne(int id) {
        return this.repo.findById(id).orElse(null);
    }

    @Override
    public Prescriptions update(int id, Prescriptions prescription) {
        prescription.setId(id);
        prescription.setIdDoctor(prescription.getIdDoctor());
        prescription.setIdMedicine(prescription.getIdMedicine());
        prescription.setIdPatient(prescription.getIdPatient());
        prescription.setDate(prescription.getDate());
        return this.repo.save(prescription);
    }

    @Override
    public void delete(int id) {
        this.repo.deleteById(id);
    }

    public static Patients getPatient(int id) {
        return repoPatients.findById(id).orElse(null);
    }

    public static Doctors getDoctor(int id) {
        return repoDoctors.findById(id).orElse(null);
    }

    public static Medicines getMedicine(int id) {
        return repoMedicines.findById(id).orElse(null);
    }
    
}
