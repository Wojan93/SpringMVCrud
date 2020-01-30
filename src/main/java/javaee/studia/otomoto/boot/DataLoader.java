package javaee.studia.otomoto.boot;

import javaee.studia.otomoto.model.Car;
import javaee.studia.otomoto.model.Image;
import javaee.studia.otomoto.repository.CarRepository;
import javaee.studia.otomoto.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

        ClassPathResource backImgFile = new ClassPathResource("static/images/audi.jpg");
        byte[] arrayPic = new byte[(int) backImgFile.contentLength()];
        backImgFile.getInputStream().read(arrayPic);
        Image blackImage = new Image(1, "audi", "jpg", arrayPic);

        // image 2
        ClassPathResource blueImgFile = new ClassPathResource("static/images/vw-passat.jpg");
        arrayPic = new byte[(int) blueImgFile.contentLength()];
        blueImgFile.getInputStream().read(arrayPic);
        Image blueImage = new Image(2, "vw-passat", "jpg", arrayPic);
        imageRepository.save(blackImage);
        imageRepository.save(blueImage);
        Set<Image> s = new HashSet<>();
        s.add(blackImage);
      //  s.add(blueImage);

        //        car1.setImages(s);
       carRepository.save(car1);
        // store image to MySQL via SpringJPA

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
   //     car2.setImages(s);
        carRepository.save(car2);

        Car car3 = new Car();
        car3.setCompany("Toyota");
        car3.setModel("Celica");
        car3.setName("Zabytek");
        car3.setColour("Blue");
        car3.setVinNumber("VWE4440012003054");
        car3.setPrice(20999.00);
        car3.setYear(1997);
        car3.setMotorCapacity(1800);
        car3.setFuelType("Benzyna");
        car3.setKilometers(210000.40);
        car3.setSafetyLock(true);
        car3.setAluWheels(true);

        carRepository.save(car3);
    }
}
