package edu.craptocraft.medimanage.service;

import java.util.List;

import edu.craptocraft.medimanage.entity.Doctors;

public interface DoctorsService {
    
    public Doctors create(Doctors doctor);

    public List<Doctors> getAll();

    public Doctors getOne(int id);

    public Doctors update(int id, Doctors doctor);

    public void delete(int id);

}
