package edu.craptocraft.medimanage.repository;

import org.springframework.data.repository.CrudRepository;

import edu.craptocraft.medimanage.entity.Doctors;

public interface DoctorsRepo extends CrudRepository<Doctors, Integer> {

}
