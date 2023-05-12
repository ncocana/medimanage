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
@Table(name = "prescriptions")
public class Prescriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "idDoctor")
    private String idDoctor;
    
    @Column(name = "idMedicine")
    private String idMedicine;
    
    @Column(name = "idPatient")
    private String idPatient;
    
    @Column(name = "date")
    private LocalDate date;

    public Prescriptions() {}

    public Prescriptions(Integer id, String idDoctor, String idMedicine, String idPatient, LocalDate date) {
        this.id = id;
        this.idDoctor = idDoctor;
        this.idMedicine = idMedicine;
        this.idPatient = idPatient;
        this.date = date;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdDoctor() {
        return this.idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getIdMedicine() {
        return this.idMedicine;
    }

    public void setIdMedicine(String idMedicine) {
        this.idMedicine = idMedicine;
    }

    public String getIdPatient() {
        return this.idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Prescriptions that = (Prescriptions) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
