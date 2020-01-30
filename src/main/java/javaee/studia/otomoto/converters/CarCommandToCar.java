package javaee.studia.otomoto.converters;

import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.model.Car;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 *
 * @author Wojciech Polubiec
 * Konwerter z ObiektuCommand na Obiekt przechowywany w Bazie danych
 * Component , klasa będzie wykrywana automatycznie w konfiguracji Springa.
 * Część wzorca projektowego Polecenie - Command
 */

@Component
public class CarCommandToCar implements Converter<CarCommand, Car> {


    public CarCommandToCar() {

    }

    @Synchronized
    @Nullable
    @Override
    public Car convert(CarCommand source) {
        if (source == null) {
            return null;
        }

        final Car car = new Car();
        car.setId(source.getId());
        car.setCompany(source.getCompany());
        car.setModel(source.getModel());
        car.setName(source.getName());
        car.setVinNumber(source.getVinNumber());
        car.setColour(source.getColour());
        car.setPrice(source.getPrice());
        car.setYear(source.getYear());
        car.setMotorCapacity(source.getMotorCapacity());
        car.setFuelType(source.getFuelType());
        car.setKilometers(source.getKilometers());
        car.setSafetyLock(source.getSafetyLock());
        car.setAluWheels(source.getAluWheels());
        car.setImage(source.getImage());


        return car;
    }
}
