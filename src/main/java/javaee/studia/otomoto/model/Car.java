package javaee.studia.otomoto.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Year;


@Entity
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    private String company;
    private String model;
    private String name;
    private String vinNumber;
    private String colour;
    private Double price;
    private Year year;
    private int motorCapacity;
    private String fuelType;
    private Double kilometers;
    private Boolean safetyLock;
    private Boolean aluWheels;

    public Car() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = Year.of(year);
    }

    public int getMotorCapacity() {
        return motorCapacity;
    }

    public void setMotorCapacity(int motorCapacity) {
        this.motorCapacity = motorCapacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
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
