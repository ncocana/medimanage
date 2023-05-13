package edu.craptocraft.medimanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.craptocraft.medimanage.entity.Doctors;

public interface DoctorsRepo extends CrudRepository<Doctors, Integer> {

    // @Query("select d from doctors d where d.email = ?1")
    // public Doctors findByEmail(String email);

}
