package javaee.studia.otomoto.service;

import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.converters.CarCommandToCar;
import javaee.studia.otomoto.converters.CarToCarCommand;
import javaee.studia.otomoto.model.CarAdvertisement;
import javaee.studia.otomoto.repository.CarAdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class CarServiceImpl implements CarService {


    private final CarAdRepository carAdRepository;
    private final CarCommandToCar carCommandToCar;
    private final CarToCarCommand carToCarCommand;

    public CarServiceImpl(CarAdRepository carAdRepository, CarCommandToCar carCommandToCar, CarToCarCommand carToCarCommand) {
        this.carAdRepository = carAdRepository;
        this.carCommandToCar = carCommandToCar;
        this.carToCarCommand = carToCarCommand;
    }

    @Override
    public Set<CarAdvertisement> getCars() {
        log.debug("I'm in the service");

        Set<CarAdvertisement> carAdvertisementSet = new HashSet<>();
        carAdRepository.findAll().iterator().forEachRemaining(carAdvertisementSet::add);
        return carAdvertisementSet;
    }

    @Override
    public CarAdvertisement findCarById(Long l) {

        Optional<CarAdvertisement> carOptional = carAdRepository.findById(l);

        if (!carOptional.isPresent()) {
            throw new RuntimeException("Not Found!");
        }

        return carOptional.get();
    }

    @Override
    @Transactional
    public CarCommand findCommandById(Long l) {
        return carToCarCommand.convert(findCarById(l));
    }

    @Override
    @Transactional
    public CarCommand saveCarCommand(CarCommand command) {
        CarAdvertisement detachedCarAdvertisement = carCommandToCar.convert(command);

        CarAdvertisement savedCarAdvertisement = carAdRepository.save(detachedCarAdvertisement);
        log.debug("Saved CarId:" + savedCarAdvertisement.getId());

        return carToCarCommand.convert(savedCarAdvertisement);
    }

    @Override
    public void deleteById(Long idToDelete) {
        carAdRepository.deleteById(idToDelete);
    }
}