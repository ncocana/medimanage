package edu.craptocraft.medimanage.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.craptocraft.medimanage.entity.Patients;
import edu.craptocraft.medimanage.repository.PatientsRepo;
import edu.craptocraft.medimanage.service.PatientsService;

@Service
public class PatientsIMPL implements PatientsService {

    @Autowired
    private PatientsRepo repo;

    @Override
    public Patients create(Patients patient) {
        patient.setId(patient.getId());
        patient.setEmail(patient.getEmail());
        patient.setName(patient.getName());
        return this.repo.save(patient);
    }

    @Override
    public List<Patients> getAll() {
        return (List<Patients>) this.repo.findAll();
    }

    @Override
    public Patients getOne(Integer id) {
        return this.repo.findById(id).orElse(null);
    }

    @Override
    public Patients update(Integer id, Patients patient) {
        patient.setId(id);
        patient.setEmail(patient.getEmail());
        patient.setName(patient.getName());
        return this.repo.save(patient);
    }

    @Override
    public void delete(Integer id) {
        this.repo.deleteById(id);
    }
    
}
