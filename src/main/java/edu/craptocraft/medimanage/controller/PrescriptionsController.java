package edu.craptocraft.medimanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.craptocraft.medimanage.entity.Prescriptions;
import edu.craptocraft.medimanage.service.implementation.PrescriptionsIMPL;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionsController {

    @Autowired
    private PrescriptionsIMPL impl;

    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody Prescriptions prescription) {
        try {
            Prescriptions createdPrescription = this.impl.create(prescription);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPrescription);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("An error ocurred: " + e.getMessage());
        }
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<?> getAll() {
        List<Prescriptions> listPrescriptions = this.impl.getAll();
        return ResponseEntity.ok(listPrescriptions);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id) {
        Prescriptions singlePrescription = this.impl.getOne(id);
        return ResponseEntity.ok(singlePrescription);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Prescriptions prescription) {
        Prescriptions updatedPrescription = this.impl.update(id, prescription);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedPrescription);
    }

    @DeleteMapping(path= "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        this.impl.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
