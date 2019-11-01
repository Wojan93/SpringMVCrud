package com.example.java.moto.boot;
import com.example.java.moto.model.Car;
import com.example.java.moto.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        car1.setName("Audi A4");
        car1.setDescription("Pali na dotyk, stan igła");
        car1.setType("1.8T B+LPG");
        car1.setCategory("SEDAN");
        car1.setPrice(24999.00);

        carRepository.save(car1);

        Car car2 = new Car();
        car2.setName("Fiat 500");
        car2.setDescription("Niemiec płakał jak sprzedawał");
        car2.setType("1.0 B");
        car2.setCategory("COUPE");
        car2.setPrice(14999.00);

        carRepository.save(car2);
    }
}
