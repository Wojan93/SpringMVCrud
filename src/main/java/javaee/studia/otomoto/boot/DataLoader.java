package javaee.studia.otomoto.boot;

import javaee.studia.otomoto.model.Car;
import javaee.studia.otomoto.repository.CarRepository;
import javaee.studia.otomoto.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class DataLoader implements CommandLineRunner {

    private CarRepository carRepository;
    private ImageRepository imageRepository;

    @Autowired
    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... strings) throws IOException {

        Car car1 = new Car();

        car1.setCompany("Audi");
        car1.setModel("A4");
        car1.setName("Doskonala");
        car1.setColour("Red");
        car1.setVinNumber("VVZ0010012003040");
        car1.setPrice(24999.00);
        car1.setYear(2008);
        car1.setMotorCapacity(1800);
        car1.setFuelType("Benzyna");
        car1.setKilometers(120000.40);
        car1.setSafetyLock(true);
        car1.setAluWheels(true);
        carRepository.save(car1);

        Car car2 = new Car();

        car2.setCompany("Volkswagen");
        car2.setModel("Passat");
        car2.setName("Igla");
        car2.setColour("Blue");
        car2.setVinNumber("VWE4440012003040");
        car2.setPrice(13999.00);
        car2.setYear(2008);
        car2.setMotorCapacity(3200);
        car2.setFuelType("Benzyna");
        car2.setKilometers(240000.40);
        car2.setSafetyLock(true);
        car2.setAluWheels(true);
        carRepository.save(car2);
    }
}
