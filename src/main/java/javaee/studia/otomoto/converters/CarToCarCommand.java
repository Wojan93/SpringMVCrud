package javaee.studia.otomoto.converters;

import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.model.Car;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CarToCarCommand implements Converter<Car, CarCommand> {


    public CarToCarCommand() {

    }


    @Synchronized
    @Nullable
    @Override
    public CarCommand convert(Car source) {
        if (source == null) {
            return null;
        }

        final CarCommand command = new CarCommand();

        command.setId(source.getId());
        command.setCompany(source.getCompany());
        command.setModel(source.getModel());
        command.setName(source.getName());
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


        return command;
    }
}
