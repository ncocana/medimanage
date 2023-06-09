package edu.craptocraft.medimanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import edu.craptocraft.medimanage.entity.Doctors;
import edu.craptocraft.medimanage.entity.Login;
import edu.craptocraft.medimanage.service.DoctorsService;
import edu.craptocraft.medimanage.service.LoginService;
import edu.craptocraft.medimanage.service.MedicinesService;
import edu.craptocraft.medimanage.service.PatientsService;
import edu.craptocraft.medimanage.service.PrescriptionsService;

@RestController
public class WebController {

    @Autowired
    private LoginService serviceLogin;

    @Autowired
    private PrescriptionsService servicePrescription;
    
    @Autowired
    private DoctorsService serviceDoctor;
    
    @Autowired
    private MedicinesService serviceMedicine;
    
    @Autowired
    private PatientsService servicePatient;

    private Doctors currentUser = null;

    @RequestMapping("/")
    public RedirectView root() {
        return new RedirectView("/home");
    }

    @RequestMapping("/home")
    public Object home() {
        if (currentUser != null) {
            return new RedirectView("prescriptions-management");
        } else {
            return new ModelAndView("/index");
        }
    }

    @RequestMapping("/login")
    public Object login() {
        if (currentUser != null) {
            return new RedirectView("prescriptions-management");
        } else {
            return new ModelAndView("/login");
        }
    }

    @PostMapping(path = "/login-process")
    public boolean loginProcess(@RequestBody Login request) {
        
        String email = request.getEmail();
        String password = request.getPassword();

        boolean doctorExists = serviceLogin.login(email, password);
        currentUser = serviceLogin.getDoctor();
        
        // If login is successful, return true.
        // If login fails, return false.
        return doctorExists;
    }

    @GetMapping(path = "/session/current-user")
    public Doctors getCurrentUser() {
        currentUser = serviceLogin.getDoctor();
        return currentUser;
    }

    @PostMapping(path = "/logout-process")
    public boolean logoutProcess() {
        
        serviceLogin.logOut();
        currentUser = null;
        
        // If log out is successful, return false.
        // If log out fails, return true.
        return serviceLogin.isLoggedIn();
    }

    @RequestMapping("/sign-up")
    public Object signUp() {
        if (currentUser != null) {
            return new RedirectView("prescriptions-management");
        } else {
            return new ModelAndView("/sign-up");
        }
    }

    @RequestMapping("/prescriptions-management")
    public Object prescriptionsManagement() {
        if (currentUser != null) {
            ModelAndView modelAndView = new ModelAndView("prescriptions-management");
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("prescriptions", servicePrescription.getAll());
            return modelAndView;
        } else {
            return new RedirectView("/home");
        }
    }

    @GetMapping("/prescriptions-management/update/{id}")
    public Object prescriptionsManagementUpdate(@PathVariable int id) {
        if (currentUser != null) {
            ModelAndView modelAndView = new ModelAndView("prescriptions-update");
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("doctors", serviceDoctor.getAll());
            modelAndView.addObject("medicines", serviceMedicine.getAll());
            modelAndView.addObject("patients", servicePatient.getAll());
            modelAndView.addObject("prescription", servicePrescription.getOne(id));
            return modelAndView;
        } else {
            return new RedirectView("/home");
        }
    }

    @GetMapping("/prescriptions-management/delete/{id}")
    public Object prescriptionsManagementDelete(@PathVariable int id) {
        if (currentUser != null) {
            servicePrescription.delete(id);
            return new RedirectView("/prescriptions-management");
        } else {
            return new RedirectView("/home");
        }
    }

    @RequestMapping("/prescriptions-management/discharge")
    public Object discharge() {
        if (currentUser != null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("discharge");
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("doctors", serviceDoctor.getAll());
            modelAndView.addObject("medicines", serviceMedicine.getAll());
            modelAndView.addObject("patients", servicePatient.getAll());
            return modelAndView;
        } else {
            return new RedirectView("/home");
        }
    }

}
