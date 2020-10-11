package javaee.studia.otomoto.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import javaee.studia.otomoto.model.enums.CarCompany;
import javaee.studia.otomoto.model.enums.Colour;
import javaee.studia.otomoto.model.enums.FuelType;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CarAdvertisement extends Advertisement {

    @Builder
    public CarAdvertisement(Long id, String title, String textAd, CarCompany carCompany, String model, String vinNumber, Colour colour,
                            Double price, int year, int motorCapacity, FuelType fuelType, Double kilometers, Boolean safetyLock, Boolean aluWheels) {
        super(id, title, textAd);
        this.carCompany = carCompany;
        this.model = model;
        this.vinNumber = vinNumber;
        this.colour = colour;
        this.price = price;
        this.year = year;
        this.motorCapacity = motorCapacity;
        this.fuelType = fuelType;
        this.kilometers = kilometers;
        this.safetyLock = safetyLock;
        this.aluWheels = aluWheels;
    }

    @NotNull(message = "is required")
    @Enumerated(EnumType.STRING)
    private CarCompany carCompany;
    @NotNull(message = "is required")
    private String model;
    @NotNull(message = "is required")
    private String vinNumber;
    @NotNull(message = "is required")
    @Enumerated(EnumType.STRING)
    private Colour colour;
    @NotNull(message = "is required")
    private Double price;
    @NotNull(message = "is required")
    @Min(value = 1900, message = "must be greater than 1900 and less than 2021")
    @Max(value = 2020, message = "must be greater than 1900 and less than 2021")
    private int year;
    @NotNull(message = "is required")
    @Min(value = 1, message = "must be valid")
    private int motorCapacity;
    @NotNull(message = "is required")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @NotNull(message = "is required")
    private Double kilometers;
    @NotNull(message = "is required")
    private Boolean safetyLock;
    @NotNull(message = "is required")
    private Boolean aluWheels;
    private Long seller;
    private Long buyer;
    private String phoneNumber;
    @Lob
    private Byte[] image;

}
