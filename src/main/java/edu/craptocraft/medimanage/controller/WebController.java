package edu.craptocraft.medimanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

    @RequestMapping("/")
    public RedirectView redirect() {
        return new RedirectView("/home");
    }

    @RequestMapping("/home")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    // @RequestMapping(value = "/loginprocess", method = RequestMethod.GET,headers = "Accept=application/json")
    // @ResponseBody
    @PostMapping(path = "/login-process")
    public ModelAndView loginprocess(@RequestBody Login request) {
        ModelAndView modelAndView = new ModelAndView();
        
        String email = request.getEmail();
        String password = request.getPassword();
        // if (email == null && password == null) {
        //     return null;
        // }
        boolean doctorExists = serviceLogin.login(email, password);
        
        if (doctorExists) {
            // Login successful
            modelAndView.setViewName("gestion");
            modelAndView.addObject("message", "Login successful");
        }
        if (!doctorExists) {
            // Login failed
            modelAndView.setViewName("login");
            modelAndView.addObject("message", "Invalid email or password");
        }
        return modelAndView;
    }

    @RequestMapping("/logoutProcess")
    public RedirectView logoutProcess() {
        serviceLogin.logOut();
        return new RedirectView("/home");
    }

    @RequestMapping("/gestion")
    public ModelAndView gestion() {
        ModelAndView modelAndView = new ModelAndView("gestion");
        modelAndView.addObject("prescriptions", servicePrescription.getAll());
        return modelAndView;
    }

    @RequestMapping("/alta")
    public ModelAndView alta() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("alta");
        modelAndView.addObject("doctors", serviceDoctor.getAll());
        modelAndView.addObject("medicines", serviceMedicine.getAll());
        modelAndView.addObject("patients", servicePatient.getAll());
        return modelAndView;
    }
    
}
