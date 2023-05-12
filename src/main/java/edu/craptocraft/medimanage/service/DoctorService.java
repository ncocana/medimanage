package edu.craptocraft.medimanage.service;

import java.util.List;

import edu.craptocraft.medimanage.entity.Doctor;

public interface DoctorService {
    
    public Doctor create(Doctor doctor);

    public List<Doctor> getAll();

    public Doctor getOne(Integer id);

    public Doctor update(Integer id, Doctor doctor);

    public void delete(Integer id);

}
