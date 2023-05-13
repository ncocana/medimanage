package edu.craptocraft.medimanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.craptocraft.medimanage.entity.Patients;
import edu.craptocraft.medimanage.service.implementation.PatientsIMPL;

@RestController
@RequestMapping("/patients")
public class PatientsController {

    @Autowired
    private PatientsIMPL impl;

    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody Patients patient) {
        try {
            Patients createdPatient = this.impl.create(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("An error ocurred: " + e.getMessage() + "\nThe email is already in use.");
        }
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<?> getAll() {
        List<Patients> listPatients = this.impl.getAll();
        return ResponseEntity.ok(listPatients);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id) {
        Patients singlePatient = this.impl.getOne(id);
        return ResponseEntity.ok(singlePatient);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Patients patient) {
        Patients updatedPatient = this.impl.update(id, patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedPatient);
    }

    @DeleteMapping(path= "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        this.impl.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
