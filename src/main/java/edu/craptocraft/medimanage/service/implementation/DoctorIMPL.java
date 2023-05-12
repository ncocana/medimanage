package edu.craptocraft.medimanage.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.craptocraft.medimanage.entity.Doctor;
import edu.craptocraft.medimanage.repository.DoctorRepo;
import edu.craptocraft.medimanage.service.DoctorService;

@Service
public class DoctorIMPL implements DoctorService {

    @Autowired
    private DoctorRepo repo;

    @Override
    public Doctor create(Doctor doctor) {
        doctor.setId(doctor.getId());
        doctor.setEmail(doctor.getEmail());
        doctor.setPassword(doctor.getPassword());
        doctor.setName(doctor.getName());
        doctor.setLastLog(doctor.getLastLog());
        doctor.setSession(doctor.getSession());
        return this.repo.save(doctor);
    }

    @Override
    public List<Doctor> getAll() {
        return (List<Doctor>) this.repo.findAll();
    }

    @Override
    public Doctor getOne(Integer id) {
        return this.repo.findById(id).orElse(null);
    }

    @Override
    public Doctor update(Integer id, Doctor doctor) {
        doctor.setId(id);
        doctor.setEmail(doctor.getEmail());
        doctor.setPassword(doctor.getPassword());
        doctor.setName(doctor.getName());
        doctor.setLastLog(doctor.getLastLog());
        doctor.setSession(doctor.getSession());
        return this.repo.save(doctor);
    }

    @Override
    public void delete(Integer id) {
        this.repo.deleteById(id);
    }

}
