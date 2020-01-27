package javaee.studia.otomoto.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car")
    private Set<Image> images = new HashSet<>();


    public Car addImage(Image image){
        image.setCar(this);
        this.images.add(image);
        return this;
    }



}
