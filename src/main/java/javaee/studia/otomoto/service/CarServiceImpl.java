package javaee.studia.otomoto.service;

import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.converters.CarCommandToCar;
import javaee.studia.otomoto.converters.CarToCarCommand;
import javaee.studia.otomoto.model.Car;
import javaee.studia.otomoto.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class CarServiceImpl implements CarService {


    private final CarRepository carRepository;
    private final CarCommandToCar carCommandToCar;
    private final CarToCarCommand carToCarCommand;

    public CarServiceImpl(CarRepository carRepository, CarCommandToCar carCommandToCar, CarToCarCommand carToCarCommand) {
        this.carRepository = carRepository;
        this.carCommandToCar = carCommandToCar;
        this.carToCarCommand = carToCarCommand;
    }

    @Override
    public Set<Car> getCars() {
        log.debug("I'm in the service");

        Set<Car> carSet = new HashSet<>();
        carRepository.findAll().iterator().forEachRemaining(carSet::add);
        return carSet;
    }

    @Override
    public Car findById(Long l) {

        Optional<Car> carOptional = carRepository.findById(l);

        if (!carOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }

        return carOptional.get();
    }

    @Override
    @Transactional
    public CarCommand findCommandById(Long l) {
        return carToCarCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public CarCommand saveCarCommand(CarCommand command) {
        Car detachedCar = carCommandToCar.convert(command);

        Car savedCar = carRepository.save(detachedCar);
        log.debug("Saved CarId:" + savedCar.getId());

        return carToCarCommand.convert(savedCar);
    }

    @Override
    public void deleteById(Long idToDelete) {
        carRepository.deleteById(idToDelete);
    }
}