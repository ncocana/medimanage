package edu.craptocraft.medimanage.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "lastLog")
    private LocalDate lastLog;
    
    @Column(name = "session", unique = true)
    private Integer session;

    public Doctor() {}

    public Doctor(String email, String password, String name, LocalDate lastLog, Integer session) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastLog = lastLog;
        this.session = session;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLastLog() {
        return this.lastLog;
    }

    public void setLastLog(LocalDate lastLog) {
        this.lastLog = lastLog;
    }

    public Integer getSession() {
        return this.session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Doctor that = (Doctor) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
