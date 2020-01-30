package javaee.studia.otomoto.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Jakub Grzechnik
 */


/**
 * Mapowanie obiektowo-relacyjne, @Entity określa nazwę tabeli w bazie danych.
 */
@Entity
@Data
public class Car {

    /**
     * @Id wskazuje, że dane pole jest unikalnym identyfikatorem obiektu, @GeneratedValue - automatyczne generowanie wartości Id, za pomocą Generatora inkrementacyjnego.
     * Pole typu Long tzn. o zakresie liczb do (2^63)-1
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;


    /**
     * @NotNull wskazuje, że dane pole jest wymagane - message udostępnia podpowiedź wyświetlaną w formularzu. Pole company jest typu łańcuchowego String (tekst)
     */
    @NotNull(message = "is required")
    private String company;
    @NotNull(message = "is required")
    private String model;
    @NotNull(message = "is required")
    private String name;
    @NotNull(message = "is required")
    private String vinNumber;
    @NotNull(message = "is required")
    private String colour;
    @NotNull(message = "is required")
    private Double price;
    @NotNull(message = "is required")

    /**
     * @Min określa wartość minimalną w polu year typu całkowitego
     * @Max określa wartość maksymalną pola year
     */
	@Min(value = 1900, message = "must be greater than 1900 and less than 2021")
    @Max(value = 2020, message = "must be greater than 1900 and less than 2021")
    private int year;
    @NotNull(message = "is required")
    @Min(value = 1, message = "must be valid")
    private int motorCapacity;
    @NotNull(message = "is required")
    private String fuelType;
    @NotNull(message = "is required")
    private Double kilometers;
    @NotNull(message = "is required")
    private Boolean safetyLock;
    @NotNull(message = "is required")
    private Boolean aluWheels;
    private Long seller;
    private Long buyer;
    /**
     * @Lob  umożliwia przechowywanie dużej ilości danych, np. dane w postaci plików graficznych. Pole typu Byte[] - klasa wrapująca typ binarny byte
     */
    @Lob
    private Byte[] image;

    /**
     * Konstruktor bezparametrowy o publicznym dostępie
     */
    public Car() {
    }

    /**
     * Publiczne getery i setery umożliwiają dostęp do danych w bazie SQL do każdego pola tej klasy.
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public Long getSeller() {
        return seller;
    }

    public void setSeller(Long seller) {
        this.seller = seller;
    }

    public Long getBuyer() {
        return buyer;
    }

    public void setBuyer(Long buyer) {
        this.buyer = buyer;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }
}
