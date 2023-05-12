package edu.craptocraft.medimanage.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.craptocraft.medimanage.entity.Doctors;
import edu.craptocraft.medimanage.repository.DoctorsRepo;
import edu.craptocraft.medimanage.service.DoctorsService;

@Service
public class DoctorsIMPL implements DoctorsService {

    @Autowired
    private DoctorsRepo repo;

    @Override
    public Doctors create(Doctors doctor) {
        doctor.setId(doctor.getId());
        doctor.setEmail(doctor.getEmail());
        doctor.setPassword(doctor.getPassword());
        doctor.setName(doctor.getName());
        doctor.setLastLog(doctor.getLastLog());
        doctor.setSession(doctor.getSession());
        return this.repo.save(doctor);
    }

    @Override
    public List<Doctors> getAll() {
        return (List<Doctors>) this.repo.findAll();
    }

    @Override
    public Doctors getOne(Integer id) {
        return this.repo.findById(id).orElse(null);
    }

    @Override
    public Doctors update(Integer id, Doctors doctor) {
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
