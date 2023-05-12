package edu.craptocraft.medimanage.service;

import java.util.List;

import edu.craptocraft.medimanage.entity.Doctors;

public interface DoctorsService {
    
    public Doctors create(Doctors doctor);

    public List<Doctors> getAll();

    public Doctors getOne(Integer id);

    public Doctors update(Integer id, Doctors doctor);

    public void delete(Integer id);

}
