package edu.craptocraft.medimanage.entity;

import java.time.LocalDate;
import java.util.Objects;

import edu.craptocraft.medimanage.service.implementation.PrescriptionsIMPL;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prescriptions")
public class Prescriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "idDoctor")
    private Doctors idDoctor;
    
    @ManyToOne
    @JoinColumn(name = "idMedicine")
    private Medicines idMedicine;
    
    @ManyToOne
    @JoinColumn(name = "idPatient")
    private Patients idPatient;
    
    @Column(name = "date")
    private LocalDate date;

    public Prescriptions() {}

    public Prescriptions(Doctors idDoctor, Medicines idMedicine, Patients idPatient, LocalDate date) {
        this.idDoctor = idDoctor;
        this.idMedicine = idMedicine;
        this.idPatient = idPatient;
        this.date = date;
    }

    public Prescriptions(int idDoctor, int idMedicine, int idPatient, LocalDate date) {
        this.idDoctor = PrescriptionsIMPL.getDoctor(idDoctor);
        this.idMedicine = PrescriptionsIMPL.getMedicine(idMedicine);
        this.idPatient = PrescriptionsIMPL.getPatient(idPatient);
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctors getIdDoctor() {
        return this.idDoctor;
    }

    public void setIdDoctor(Doctors idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Medicines getIdMedicine() {
        return this.idMedicine;
    }

    public void setIdMedicine(Medicines idMedicine) {
        this.idMedicine = idMedicine;
    }

    public Patients getIdPatient() {
        return this.idPatient;
    }

    public void setIdPatient(Patients idPatient) {
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
