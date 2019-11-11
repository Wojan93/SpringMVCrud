package com.example.java.moto.boot;

import com.example.java.moto.model.Car;
import com.example.java.moto.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
public class DataLoader implements CommandLineRunner {

    private CarRepository carRepository;

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        Car car1 = new Car();
        car1.setCompany("Audi");
        car1.setModel("A4");
        car1.setName("B6");
        car1.setVinNumber("WVX123QWE23DFG");
        car1.setColour("green");
        car1.setYear(LocalDate.of(2014, Month.JANUARY, 1));
        car1.setMotorCapacity(1.9);
        car1.setFuelType("DIESEL");
        car1.setKilometers(209235.20);
        car1.setSafetyLock(true);
        car1.setAluWheels(true);



        carRepository.save(car1);

        Car car2 = new Car();
        car2.setCompany("Skoda");
        car2.setModel("Octavia");
        car2.setName("II");
        car2.setVinNumber("SKD123QWE23DFG");
        car2.setColour("black");
        car2.setYear(LocalDate.of(2017, Month.DECEMBER, 1));
        car2.setMotorCapacity(1.6);
        car2.setFuelType("BENZYNA");
        car2.setKilometers(209235.20);
        car2.setSafetyLock(true);
        car2.setAluWheels(false);

        carRepository.save(car2);
    }
}
