package javaee.studia.otomoto.commands;


import javaee.studia.otomoto.model.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CarCommand {


    private Long id;
    private String company;
    private String model;
    private String name;
    private String vinNumber;
    private String colour;
    private Double price;
    private int year;
    private int motorCapacity;
    private String fuelType;
    private Double kilometers;
    private Boolean safetyLock;
    private Boolean aluWheels;
    protected Set<ImageCommand> images = new HashSet<>();
}
