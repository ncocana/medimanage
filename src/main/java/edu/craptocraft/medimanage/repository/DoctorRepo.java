package edu.craptocraft.medimanage.repository;

import org.springframework.data.repository.CrudRepository;

import edu.craptocraft.medimanage.entity.Doctor;

public interface DoctorRepo extends CrudRepository<Doctor, Integer> {

}
