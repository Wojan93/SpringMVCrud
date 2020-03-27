package javaee.studia.otomoto.service;

        import javaee.studia.otomoto.commands.CarCommand;
        import javaee.studia.otomoto.model.CarAdvertisement;

        import java.util.Set;

public interface CarService {

    Set<CarAdvertisement> getCars();

    CarAdvertisement findCarById(Long l);

    CarCommand findCommandById(Long l);

    CarCommand saveCarCommand(CarCommand command);

    void deleteById(Long idToDelete);
}
