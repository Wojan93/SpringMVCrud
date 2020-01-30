package javaee.studia.otomoto.service;

import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.model.Car;

import java.util.Set;

/**
 *
 * @author Mateusz Wilk
 */

public interface CarService {

    Set<Car> getCars();

    Car findById(Long l);

    CarCommand findCommandById(Long l);

    CarCommand saveCarCommand(CarCommand command);

    void deleteById(Long idToDelete);
}
