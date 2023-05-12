package edu.craptocraft.medimanage.repository;

import org.springframework.data.repository.CrudRepository;

import edu.craptocraft.medimanage.entity.Prescriptions;

public interface PrescriptionsRepo extends CrudRepository<Prescriptions, Integer> {

}
