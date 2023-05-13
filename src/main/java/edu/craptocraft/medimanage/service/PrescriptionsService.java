package edu.craptocraft.medimanage.service;

import java.util.List;

import edu.craptocraft.medimanage.entity.Prescriptions;

public interface PrescriptionsService {
    
    public Prescriptions create(Prescriptions prescription);

    public List<Prescriptions> getAll();

    public Prescriptions getOne(int id);

    public Prescriptions update(int id, Prescriptions prescription);

    public void delete(int id);

}
