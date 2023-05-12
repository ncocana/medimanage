package edu.craptocraft.medimanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.craptocraft.medimanage.entity.Medicines;
import edu.craptocraft.medimanage.service.implementation.MedicinesIMPL;

@RestController
@RequestMapping("/medicines")
public class MedicinesController {

    @Autowired
    private MedicinesIMPL impl;

    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody Medicines medicine) {
        try {
            Medicines createdMedicine = this.impl.create(medicine);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMedicine);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("An error ocurred: " + e.getMessage());
        }
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<?> getAll() {
        List<Medicines> listMedicines = this.impl.getAll();
        return ResponseEntity.ok(listMedicines);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        Medicines singleMedicine = this.impl.getOne(id);
        return ResponseEntity.ok(singleMedicine);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Medicines medicine) {
        Medicines updatedMedicine = this.impl.update(id, medicine);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedMedicine);
    }

    @DeleteMapping(path= "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        this.impl.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
