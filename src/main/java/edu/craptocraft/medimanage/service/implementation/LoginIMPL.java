package edu.craptocraft.medimanage.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.craptocraft.medimanage.entity.Doctors;
import edu.craptocraft.medimanage.service.DoctorsService;
import edu.craptocraft.medimanage.service.LoginService;

@Service
public class LoginIMPL implements LoginService {

    @Autowired
    private DoctorsService serviceDoctor;

    private boolean isLogged = false;
    private Doctors doctorLogged = null;
    private Random random = null;

    @Override
    public boolean login(String email, String password) {
        List<Doctors> doctors = serviceDoctor.getAll();
        for(Doctors doctor : doctors) {
            if (doctor != null && doctor.getEmail().equals(email) && doctor.getPassword().equals(password)) {
                doctor.setLastLog(LocalDate.now());

                int maxRetries = 100; // Maximum number of retries
                int retryCount = 0; // Counter for retries
                boolean success = false;
                while (!success && retryCount < maxRetries) {
                    try {
                        random = new Random();
                        Integer session = random.nextInt(1000000000);
                        doctor.setSession(session);
                        serviceDoctor.update(doctor.getId(), doctor);
                        success = true; // If no exception is thrown, set success to true
                    } catch (DataIntegrityViolationException e) {
                        // Handle the exception if needed
                        retryCount++; // Increment the retry count
                    }
                }

                this.setLoggedIn(true);
                this.setDoctor(doctor);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isLoggedIn() {
        return this.isLogged;
    }

    private void setLoggedIn(boolean value) {
        this.isLogged = value;
    }

    @Override
    public Doctors getDoctor() {
        return this.doctorLogged;
    }

    private void setDoctor(Doctors doctor) {
        this.doctorLogged = doctor;
    }

    @Override
    public void logOut() {
        this.isLogged = false;
        this.doctorLogged.setSession(null);
        serviceDoctor.update(doctorLogged.getId(), doctorLogged);
        this.doctorLogged = null;
        this.random = null;
    }
    
}
