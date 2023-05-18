package edu.craptocraft.medimanage.service.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public boolean login(String email, String password) {
        List<Doctors> doctors = serviceDoctor.getAll();
        for(Doctors doctor : doctors) {
            if (doctor != null && doctor.getEmail().equals(email) && doctor.getPassword().equals(password)) {
                doctor.setSession(105);
                doctor.setLastLog(LocalDate.now());
                serviceDoctor.update(doctor.getId(), doctor);
                this.setLoggedIn(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isLoggedIn() {
        return this.isLogged;
    }

    @Override
    public void setLoggedIn(boolean value) {
        this.isLogged = value;
    }

    @Override
    public Doctors getDoctor() {
        return this.doctorLogged;
    }

    @Override
    public void logOut() {
        this.isLogged = false;
        this.doctorLogged = null;
    }
    
}
