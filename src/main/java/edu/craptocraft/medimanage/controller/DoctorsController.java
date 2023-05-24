package edu.craptocraft.medimanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.craptocraft.medimanage.entity.Doctors;
import edu.craptocraft.medimanage.service.DoctorsService;

@RestController
@RequestMapping("/doctors")
public class DoctorsController {

    @Autowired
    private DoctorsService serviceDoctor;

    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody Doctors doctor) {
        try {
            Doctors createdDoctor = this.serviceDoctor.create(doctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctor);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("An error ocurred: " + e.getMessage() + "\nThe email or session are already in use.");
        }
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<?> getAll() {
        List<Doctors> listDoctors = this.serviceDoctor.getAll();
        return ResponseEntity.ok(listDoctors);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id) {
        Doctors singleDoctor = this.serviceDoctor.getOne(id);
        return ResponseEntity.ok(singleDoctor);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Doctors doctor) {
        Doctors updatedDoctor = this.serviceDoctor.update(id, doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedDoctor);
    }

    @DeleteMapping(path= "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        this.serviceDoctor.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
