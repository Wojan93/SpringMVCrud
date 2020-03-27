package javaee.studia.otomoto.commands;

import javaee.studia.otomoto.model.MotorcycleCompany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MotorcycleCommand {

    private Long id;
    private String title;
    private String textAd;
    private MotorcycleCompany motorcycleCompany;
    private String model;
    private String name;
    private String vinNumber;
    private String colour;
    private Double price;
    private int year;
    private int motorCapacity;
    private Double kilometers;
    private Long seller;
    private Long buyer;
    private Byte[] image;
}
