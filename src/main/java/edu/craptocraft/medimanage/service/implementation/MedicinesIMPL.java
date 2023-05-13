package edu.craptocraft.medimanage.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.craptocraft.medimanage.entity.Medicines;
import edu.craptocraft.medimanage.repository.MedicinesRepo;
import edu.craptocraft.medimanage.service.MedicinesService;

@Service
public class MedicinesIMPL implements MedicinesService {

    @Autowired
    private MedicinesRepo repo;

    @Override
    public Medicines create(Medicines medicine) {
        medicine.setId(medicine.getId());
        medicine.setName(medicine.getName());
        medicine.setTmax(medicine.getTmax());
        medicine.setTmin(medicine.getTmin());
        return this.repo.save(medicine);
    }

    @Override
    public List<Medicines> getAll() {
        return (List<Medicines>) this.repo.findAll();
    }

    @Override
    public Medicines getOne(int id) {
        return this.repo.findById(id).orElse(null);
    }

    @Override
    public Medicines update(int id, Medicines medicine) {
        medicine.setId(id);
        medicine.setName(medicine.getName());
        medicine.setTmax(medicine.getTmax());
        medicine.setTmin(medicine.getTmin());
        return this.repo.save(medicine);
    }

    @Override
    public void delete(int id) {
        this.repo.deleteById(id);
    }
    
}
