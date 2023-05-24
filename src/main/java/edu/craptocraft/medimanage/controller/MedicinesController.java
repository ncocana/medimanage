package edu.craptocraft.medimanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.craptocraft.medimanage.entity.Medicines;
import edu.craptocraft.medimanage.service.MedicinesService;

@RestController
@RequestMapping("/medicines")
public class MedicinesController {

    @Autowired
    private MedicinesService serviceMedicine;

    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody Medicines medicine) {
        try {
            Medicines createdMedicine = this.serviceMedicine.create(medicine);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMedicine);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("An error ocurred: " + e.getMessage());
        }
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<?> getAll() {
        List<Medicines> listMedicines = this.serviceMedicine.getAll();
        return ResponseEntity.ok(listMedicines);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id) {
        Medicines singleMedicine = this.serviceMedicine.getOne(id);
        return ResponseEntity.ok(singleMedicine);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Medicines medicine) {
        Medicines updatedMedicine = this.serviceMedicine.update(id, medicine);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedMedicine);
    }

    @DeleteMapping(path= "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        this.serviceMedicine.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
