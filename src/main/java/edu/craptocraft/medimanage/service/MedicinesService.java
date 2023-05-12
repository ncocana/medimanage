package edu.craptocraft.medimanage.service;

import java.util.List;

import edu.craptocraft.medimanage.entity.Medicines;

public interface MedicinesService {
    
    public Medicines create(Medicines medicine);

    public List<Medicines> getAll();

    public Medicines getOne(Integer id);

    public Medicines update(Integer id, Medicines medicine);

    public void delete(Integer id);

}
