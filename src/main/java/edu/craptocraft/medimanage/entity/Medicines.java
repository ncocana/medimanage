package edu.craptocraft.medimanage.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicines")
public class Medicines {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "tmax")
    private Float tmax;
    
    @Column(name = "tmin")
    private Float tmin;

    public Medicines() {}

    public Medicines(String name, Float tmax, Float tmin) {
        this.name = name;
        this.tmax = tmax;
        this.tmin = tmin;
    }

    public Medicines(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getTmax() {
        return this.tmax;
    }

    public void setTmax(Float tmax) {
        this.tmax = tmax;
    }

    public Float getTmin() {
        return this.tmin;
    }

    public void setTmin(Float tmin) {
        this.tmin = tmin;
    }    

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Medicines that = (Medicines) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
