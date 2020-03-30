package javaee.studia.otomoto.converters;

import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.model.CarAdvertisement;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CarToCarCommand implements Converter<CarAdvertisement, CarCommand> {


    public CarToCarCommand() {

    }


    @Synchronized
    @Nullable
    @Override
    public CarCommand convert(CarAdvertisement source) {
        if (source == null) {
            return null;
        }

        final CarCommand command = new CarCommand();

        command.setId(source.getId());
        command.setCarCompany(source.getCarCompany());
        command.setModel(source.getModel());
        command.setTitle(source.getTitle());
        command.setVinNumber(source.getVinNumber());
        command.setColour(source.getColour());
        command.setPrice(source.getPrice());
        command.setYear(source.getYear());
        command.setMotorCapacity(source.getMotorCapacity());
        command.setFuelType(source.getFuelType());
        command.setKilometers(source.getKilometers());
        command.setSafetyLock(source.getSafetyLock());
        command.setAluWheels(source.getAluWheels());
        command.setImage(source.getImage());
        command.setPhoneNumber(source.getPhoneNumber());


        return command;
    }
}
