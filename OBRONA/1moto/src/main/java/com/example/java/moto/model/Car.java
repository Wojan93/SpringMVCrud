package com.example.java.moto.model;

import javax.persistence.Entity;
import java.time.LocalDate;



@Entity
public class Car extends BaseEntity {

    private String company;
    private String model;
    private String name;
    private String vinNumber;
    private String colour;
    private LocalDate year;
    private Double motorCapacity;
    private String fuelType;
    private Double kilometers;
    private Boolean safetyLock;
    private Boolean aluWheels;

    public Car() {
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }



    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public Double getMotorCapacity() {
        return motorCapacity;
    }

    public void setMotorCapacity(Double motorCapacity) {
        this.motorCapacity = motorCapacity;
    }

    public Double getKilometers() {
        return kilometers;
    }

    public void setKilometers(Double kilometers) {
        this.kilometers = kilometers;
    }

    public Boolean getSafetyLock() {
        return safetyLock;
    }

    public void setSafetyLock(Boolean safetyLock) {
        this.safetyLock = safetyLock;
    }

    public Boolean getAluWheels() {
        return aluWheels;
    }

    public void setAluWheels(Boolean aluWheels) {
        this.aluWheels = aluWheels;
    }

}
