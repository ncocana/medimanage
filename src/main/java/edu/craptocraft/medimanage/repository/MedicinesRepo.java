package edu.craptocraft.medimanage.repository;

import org.springframework.data.repository.CrudRepository;

import edu.craptocraft.medimanage.entity.Medicines;

public interface MedicinesRepo extends CrudRepository<Medicines, Integer> {

}
