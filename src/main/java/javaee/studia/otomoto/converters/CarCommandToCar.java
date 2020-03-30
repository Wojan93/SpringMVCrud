package javaee.studia.otomoto.converters;

import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.model.CarAdvertisement;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CarCommandToCar implements Converter<CarCommand, CarAdvertisement> {


    public CarCommandToCar() {

    }

    @Synchronized
    @Nullable
    @Override
    public CarAdvertisement convert(CarCommand source) {
        if (source == null) {
            return null;
        }

        final CarAdvertisement carAdvertisement = new CarAdvertisement();

        carAdvertisement.setId(source.getId());
        carAdvertisement.setCarCompany(source.getCarCompany());
        carAdvertisement.setModel(source.getModel());
        carAdvertisement.setTitle(source.getTitle());
        carAdvertisement.setVinNumber(source.getVinNumber());
        carAdvertisement.setColour(source.getColour());
        carAdvertisement.setPrice(source.getPrice());
        carAdvertisement.setYear(source.getYear());
        carAdvertisement.setMotorCapacity(source.getMotorCapacity());
        carAdvertisement.setFuelType(source.getFuelType());
        carAdvertisement.setKilometers(source.getKilometers());
        carAdvertisement.setSafetyLock(source.getSafetyLock());
        carAdvertisement.setAluWheels(source.getAluWheels());
        carAdvertisement.setImage(source.getImage());
        carAdvertisement.setPhoneNumber(source.getPhoneNumber());

        return carAdvertisement;
    }
}
