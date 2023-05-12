package edu.craptocraft.medimanage.repository;

import org.springframework.data.repository.CrudRepository;

import edu.craptocraft.medimanage.entity.Patients;

public interface PatientsRepo extends CrudRepository<Patients, Integer> {

}
