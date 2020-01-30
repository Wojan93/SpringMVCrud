package javaee.studia.otomoto.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long seller;
    private Long buyer;
    private Byte[] image;
}
