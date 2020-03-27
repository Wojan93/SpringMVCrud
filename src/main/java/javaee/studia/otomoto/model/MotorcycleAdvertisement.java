package javaee.studia.otomoto.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MotorcycleAdvertisement extends Advertisement {

    @Builder
    public MotorcycleAdvertisement(Long id, String title, String textAd, MotorcycleCompany motorcycleCompany, String model, String vinNumber, String colour,
                            Double price, int year, int motorCapacity, Double kilometers) {
        super(id, title, textAd);
        this.motorcycleCompany = motorcycleCompany;
        this.model = model;
        this.vinNumber = vinNumber;
        this.colour = colour;
        this.price = price;
        this.year = year;
        this.motorCapacity = motorCapacity;
        this.kilometers = kilometers;

    }

    @NotNull(message = "is required")
    @Enumerated(EnumType.STRING)
    private MotorcycleCompany motorcycleCompany;
    @NotNull(message = "is required")
    private String model;
    @NotNull(message = "is required")
    private String vinNumber;
    @NotNull(message = "is required")
    private String colour;
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
    private Double kilometers;
    private Long seller;
    private Long buyer;
    private String phoneNumber;
    @Lob
    private Byte[] image;
}
