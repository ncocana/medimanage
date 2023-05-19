package edu.craptocraft.medimanage.service;

import edu.craptocraft.medimanage.entity.Doctors;

public interface LoginService {
    
    boolean login(String email, String password);

    boolean isLoggedIn();
    
    Doctors getDoctor();

    void logOut();
    
}
