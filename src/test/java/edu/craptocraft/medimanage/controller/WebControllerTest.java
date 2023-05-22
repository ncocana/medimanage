package edu.craptocraft.medimanage.controller;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import edu.craptocraft.medimanage.entity.Doctors;
import edu.craptocraft.medimanage.entity.Login;
import edu.craptocraft.medimanage.entity.Medicines;
import edu.craptocraft.medimanage.entity.Patients;
import edu.craptocraft.medimanage.entity.Prescriptions;
import edu.craptocraft.medimanage.service.DoctorsService;
import edu.craptocraft.medimanage.service.PrescriptionsService;

import static org.junit.Assert.*;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebControllerTest {

    @Autowired
    private DoctorsController doctorsController;

    @Autowired
    private PrescriptionsController prescriptionsController;
    
    @Autowired
    private WebController webController;

    @Autowired
    private DoctorsService doctorsService;

    @Autowired
    private PrescriptionsService prescriptionsService;
    
    private Doctors doctor = null;
    private Login login = null;
    private Prescriptions prescription = null;

    @Test
    public void test_root() {
        RedirectView redirectView = webController.root();
        assertEquals("/home", redirectView.getUrl());
    }

    @Test
    public void test_homeWithCurrentUser() {
        doctor = new Doctors("test@example.com", "password", "test", LocalDate.now());
        doctorsService.create(doctor);
        ResponseEntity<?> response = doctorsController.getOne(doctor.getId());
        Doctors createdDoctor = (Doctors) response.getBody();
        login = new Login(createdDoctor.getEmail(), createdDoctor.getPassword());
        webController.loginProcess(login);

        Object result = webController.home();
        assertTrue(result instanceof RedirectView);
        assertEquals("prescriptions-management", ((RedirectView) result).getUrl());

        webController.logoutProcess();
        ResponseEntity<?> responseDelete = doctorsController.delete(createdDoctor.getId());
        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_homeWithoutCurrentUser() {
        Object result = webController.home();
        assertTrue(result instanceof ModelAndView);
        assertEquals("index", ((ModelAndView) result).getViewName());
    }

    @Test
    public void test_loginWithCurrentUser() {
        doctor = new Doctors("test@example.com", "password", "test", LocalDate.now());
        doctorsService.create(doctor);
        ResponseEntity<?> response = doctorsController.getOne(doctor.getId());
        Doctors createdDoctor = (Doctors) response.getBody();
        login = new Login(createdDoctor.getEmail(), createdDoctor.getPassword());
        webController.loginProcess(login);
        
        Object result = webController.login();
        assertTrue(result instanceof RedirectView);
        assertEquals("prescriptions-management", ((RedirectView) result).getUrl());

        webController.logoutProcess();
        ResponseEntity<?> responseDelete = doctorsController.delete(createdDoctor.getId());
        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_loginWithoutCurrentUser() {
        Object result = webController.login();
        assertTrue(result instanceof ModelAndView);
        assertEquals("/login", ((ModelAndView) result).getViewName());
    }

    @Test
    public void test_loginProcessWithInvalidCredentials() {
        Login login2 = new Login("test2@example.com", "password");

        boolean result = webController.loginProcess(login2);
        assertFalse(result);
        assertNull(webController.getCurrentUser());
    }

    @Test
    public void test_prescriptionsManagementWithCurrentUser() {
        doctor = new Doctors("test@example.com", "password", "test", LocalDate.now());
        doctorsService.create(doctor);
        ResponseEntity<?> response = doctorsController.getOne(doctor.getId());
        Doctors createdDoctor = (Doctors) response.getBody();
        login = new Login(createdDoctor.getEmail(), createdDoctor.getPassword());
        webController.loginProcess(login);

        Object result = webController.prescriptionsManagement();
        assertTrue(result instanceof ModelAndView);
        ModelAndView modelAndView = (ModelAndView) result;
        assertEquals("prescriptions-management", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel().get("currentUser"));
        assertNotNull(modelAndView.getModel().get("prescriptions"));

        webController.logoutProcess();
        ResponseEntity<?> responseDelete = doctorsController.delete(createdDoctor.getId());
        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_prescriptionsManagementWithoutCurrentUser() {
        Object result = webController.prescriptionsManagement();
        assertTrue(result instanceof RedirectView);
        assertEquals("/home", ((RedirectView) result).getUrl());
    }

    @Test
    public void test_prescriptionsManagementUpdateWithCurrentUser() {
        doctor = new Doctors("test@example.com", "password", "test", LocalDate.now());
        doctorsService.create(doctor);
        ResponseEntity<?> responseDoctors = doctorsController.getOne(doctor.getId());
        Doctors createdDoctor = (Doctors) responseDoctors.getBody();

        prescription = new Prescriptions(new Doctors(createdDoctor.getId()), new Medicines(1), new Patients(1), LocalDate.of(2023, 05, 15));
        prescriptionsService.create(prescription);
        ResponseEntity<?> responsePrescription = prescriptionsController.getOne(prescription.getId());
        Prescriptions createdPrescription = (Prescriptions) responsePrescription.getBody();

        login = new Login(createdDoctor.getEmail(), createdDoctor.getPassword());
        webController.loginProcess(login);

        Object result = webController.prescriptionsManagementUpdate(createdPrescription.getId());
        assertTrue(result instanceof ModelAndView);
        ModelAndView modelAndView = (ModelAndView) result;
        assertEquals("prescriptions-update", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel().get("currentUser"));
        assertNotNull(modelAndView.getModel().get("doctors"));
        assertNotNull(modelAndView.getModel().get("medicines"));
        assertNotNull(modelAndView.getModel().get("patients"));
        assertNotNull(modelAndView.getModel().get("prescription"));

        webController.logoutProcess();

        ResponseEntity<?> responseDeletePrescription = prescriptionsController.delete(createdPrescription.getId());
        assertNotNull(responseDeletePrescription);
        assertEquals(HttpStatus.OK, responseDeletePrescription.getStatusCode());

        ResponseEntity<?> responseDeleteDoctor = doctorsController.delete(createdDoctor.getId());
        assertNotNull(responseDeleteDoctor);
        assertEquals(HttpStatus.OK, responseDeleteDoctor.getStatusCode());
    }

    @Test
    public void test_prescriptionsManagementUpdateWithoutCurrentUser() {
        int id = 123;
        Object result = webController.prescriptionsManagementUpdate(id);
        assertTrue(result instanceof RedirectView);
        assertEquals("/home", ((RedirectView) result).getUrl());
    }

    @Test
    public void test_prescriptionsManagementDeleteWithCurrentUser() {
        doctor = new Doctors("test@example.com", "password", "test", LocalDate.now());
        doctorsService.create(doctor);
        ResponseEntity<?> responseDoctors = doctorsController.getOne(doctor.getId());
        Doctors createdDoctor = (Doctors) responseDoctors.getBody();

        prescription = new Prescriptions(new Doctors(createdDoctor.getId()), new Medicines(1), new Patients(1), LocalDate.of(2023, 05, 15));
        prescriptionsService.create(prescription);
        ResponseEntity<?> responsePrescription = prescriptionsController.getOne(prescription.getId());
        Prescriptions createdPrescription = (Prescriptions) responsePrescription.getBody();

        login = new Login(createdDoctor.getEmail(), createdDoctor.getPassword());
        webController.loginProcess(login);

        Object result = webController.prescriptionsManagementDelete(createdDoctor.getId());
        assertTrue(result instanceof RedirectView);
        assertEquals("/prescriptions-management", ((RedirectView) result).getUrl());

        webController.logoutProcess();

        ResponseEntity<?> responseDeletePrescription = prescriptionsController.delete(createdPrescription.getId());
        assertNotNull(responseDeletePrescription);
        assertEquals(HttpStatus.OK, responseDeletePrescription.getStatusCode());

        ResponseEntity<?> responseDeleteDoctor = doctorsController.delete(createdDoctor.getId());
        assertNotNull(responseDeleteDoctor);
        assertEquals(HttpStatus.OK, responseDeleteDoctor.getStatusCode());
    }

    @Test
    public void test_prescriptionsManagementDeleteWithoutCurrentUser() {
        int id = 123;
        Object result = webController.prescriptionsManagementDelete(id);
        assertTrue(result instanceof RedirectView);
        assertEquals("/home", ((RedirectView) result).getUrl());
    }

    @Test
    public void test_dischargeWithCurrentUser() {
        doctor = new Doctors("test@example.com", "password", "test", LocalDate.now());
        doctorsService.create(doctor);
        ResponseEntity<?> response = doctorsController.getOne(doctor.getId());
        Doctors createdDoctor = (Doctors) response.getBody();
        login = new Login(createdDoctor.getEmail(), createdDoctor.getPassword());
        webController.loginProcess(login);

        Object result = webController.discharge();
        assertTrue(result instanceof ModelAndView);
        ModelAndView modelAndView = (ModelAndView) result;
        assertEquals("discharge", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel().get("currentUser"));
        assertNotNull(modelAndView.getModel().get("doctors"));
        assertNotNull(modelAndView.getModel().get("medicines"));
        assertNotNull(modelAndView.getModel().get("patients"));

        webController.logoutProcess();
        ResponseEntity<?> responseDelete = doctorsController.delete(createdDoctor.getId());
        assertNotNull(responseDelete);
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

    @Test
    public void test_dischargeWithoutCurrentUser() {
        Object result = webController.discharge();
        assertTrue(result instanceof RedirectView);
        assertEquals("/home", ((RedirectView) result).getUrl());
    }

}
