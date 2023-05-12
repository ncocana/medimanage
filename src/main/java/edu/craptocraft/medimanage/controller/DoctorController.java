package edu.craptocraft.medimanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.craptocraft.medimanage.entity.Doctor;
import edu.craptocraft.medimanage.service.implementation.DoctorIMPL;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorIMPL impl;

    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody Doctor doctor) {
        try {
            Doctor createdDoctor = this.impl.create(doctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctor);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("An error ocurred: " + e.getMessage() + "\nThe email or session are already in use.");
        }
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<?> getAll() {
        List<Doctor> listDoctor = this.impl.getAll();
        return ResponseEntity.ok(listDoctor);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        Doctor singleDoctor = this.impl.getOne(id);
        return ResponseEntity.ok(singleDoctor);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<?> updateLanguage(@PathVariable Integer id, @RequestBody Doctor doctor) {
        Doctor updatedDoctor = this.impl.update(id, doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedDoctor);
    }

    @DeleteMapping(path= "delete/{id}")
    public ResponseEntity<?> deleteLanguage(@PathVariable Integer id) {
        this.impl.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
