package edu.craptocraft.medimanage.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.craptocraft.medimanage.entity.Doctors;

@Repository
public interface DoctorsRepo extends CrudRepository<Doctors, Integer> {

}
