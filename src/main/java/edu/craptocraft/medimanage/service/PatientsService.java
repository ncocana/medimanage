package edu.craptocraft.medimanage.service;

import java.util.List;

import edu.craptocraft.medimanage.entity.Patients;

public interface PatientsService {
    
    public Patients create(Patients patient);

    public List<Patients> getAll();

    public Patients getOne(int id);

    public Patients update(int id, Patients patient);

    public void delete(int id);

}
