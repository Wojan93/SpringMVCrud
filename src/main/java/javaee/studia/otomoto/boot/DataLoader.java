package javaee.studia.otomoto.boot;

import javaee.studia.otomoto.model.*;
import javaee.studia.otomoto.repository.CarAdRepository;
import javaee.studia.otomoto.repository.MtAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class DataLoader implements CommandLineRunner {

    private CarAdRepository carAdRepository;
    private MtAdRepository mtAdRepository;

    private Byte[] byteToByteConv(ClassPathResource cpr) throws IOException {

        byte[] arrayPic = new byte[(int) cpr.contentLength()];
        cpr.getInputStream().read(arrayPic);
        Byte[] byteObjects = new Byte[arrayPic.length];

        int i = 0;
        // Associating Byte array values with bytes. (byte[] to Byte[])
        for (byte b : arrayPic)
            byteObjects[i++] = b;

        return byteObjects;
    }

    @Autowired
    public void setCarAdRepository(CarAdRepository carAdRepository) {
        this.carAdRepository = carAdRepository;
    }

    @Autowired
    public void setMtAdRepository(MtAdRepository mtAdRepository) {
        this.mtAdRepository = mtAdRepository;
    }

    @Override
    public void run(String... strings) throws IOException {
        this.carAdRepository.deleteAll();
        this.mtAdRepository.deleteAll();

        CarAdvertisement carAdvertisement1 = new CarAdvertisement();

        carAdvertisement1.setTitle("Doskonala");
        carAdvertisement1.setTextAd("Super Extra");
        carAdvertisement1.setCarCompany(CarCompany.AUDI);
        carAdvertisement1.setModel("A4");
        carAdvertisement1.setColour("Red");
        carAdvertisement1.setVinNumber("VVZ0010012003040");
        carAdvertisement1.setPrice(24999.00);
        carAdvertisement1.setYear(2008);
        carAdvertisement1.setMotorCapacity(1800);
        carAdvertisement1.setFuelType("Benzyna");
        carAdvertisement1.setKilometers(120000.40);
        carAdvertisement1.setSafetyLock(true);
        carAdvertisement1.setAluWheels(true);
        carAdvertisement1.setPhoneNumber("100100100");
        carAdvertisement1.setImage(byteToByteConv(new ClassPathResource("static/images/audi.jpg")));

        carAdRepository.save(carAdvertisement1);
        // store image to MySQL via SpringJPA 

        CarAdvertisement carAdvertisement2 = new CarAdvertisement();

        carAdvertisement2.setTitle("Doskonala2");
        carAdvertisement2.setTextAd("Super Extra2");
        carAdvertisement2.setCarCompany(CarCompany.VOLKSWAGEN);
        carAdvertisement2.setModel("Passat");
        carAdvertisement2.setColour("Blue");
        carAdvertisement2.setVinNumber("VWE4440012003040");
        carAdvertisement2.setPrice(13999.00);
        carAdvertisement2.setYear(2008);
        carAdvertisement2.setMotorCapacity(3200);
        carAdvertisement2.setFuelType("Benzyna");
        carAdvertisement2.setKilometers(240000.40);
        carAdvertisement2.setSafetyLock(true);
        carAdvertisement2.setAluWheels(true);
        carAdvertisement2.setPhoneNumber("200200200");
        carAdvertisement2.setImage(byteToByteConv(new ClassPathResource("static/images/vw-passat.jpg")));

        carAdRepository.save(carAdvertisement2);

        CarAdvertisement carAdvertisement3 = new CarAdvertisement();

        carAdvertisement3.setTitle("Doskonala3");
        carAdvertisement3.setTextAd("Super Extra3");
        carAdvertisement3.setCarCompany(CarCompany.TOYOTA);
        carAdvertisement3.setModel("Celica");
        carAdvertisement3.setColour("Blue");
        carAdvertisement3.setVinNumber("VWE4440012003054");
        carAdvertisement3.setPrice(20999.00);
        carAdvertisement3.setYear(1997);
        carAdvertisement3.setMotorCapacity(1800);
        carAdvertisement3.setFuelType("Benzyna");
        carAdvertisement3.setKilometers(210000.40);
        carAdvertisement3.setSafetyLock(true);
        carAdvertisement3.setAluWheels(true);
        carAdvertisement3.setPhoneNumber("300300300");
        carAdvertisement3.setImage(byteToByteConv(new ClassPathResource("static/images/Toyota.jpg")));
        carAdRepository.save(carAdvertisement3);

        MotorcycleAdvertisement motorcycleAdvertisement1 = new MotorcycleAdvertisement();

        motorcycleAdvertisement1.setTitle("Jawka");
        motorcycleAdvertisement1.setTextAd("Super Extra Jawa");
        motorcycleAdvertisement1.setMotorcycleCompany(MotorcycleCompany.JAWA);
        motorcycleAdvertisement1.setModel("350 TS");
        motorcycleAdvertisement1.setColour("Black");
        motorcycleAdvertisement1.setVinNumber("JAWA4440012003054");
        motorcycleAdvertisement1.setPrice(290.00);
        motorcycleAdvertisement1.setYear(1990);
        motorcycleAdvertisement1.setMotorCapacity(350);
        motorcycleAdvertisement1.setKilometers(2100.40);
        motorcycleAdvertisement1.setPhoneNumber("0700880323");
        motorcycleAdvertisement1.setImage(byteToByteConv(new ClassPathResource("static/images/jawa-350.jpg")));

        mtAdRepository.save(motorcycleAdvertisement1);
    }
}
