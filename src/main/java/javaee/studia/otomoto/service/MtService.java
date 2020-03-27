package javaee.studia.otomoto.service;

import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.commands.MotorcycleCommand;
import javaee.studia.otomoto.model.CarAdvertisement;
import javaee.studia.otomoto.model.MotorcycleAdvertisement;

import java.util.Set;

public interface MtService  {

    Set<MotorcycleAdvertisement> getMotorcycles();

    MotorcycleAdvertisement findMotorcycleById(Long l);

    MotorcycleCommand findCommandById(Long l);

    MotorcycleCommand saveMotorcycleCommand(MotorcycleCommand command);

    void deleteById(Long idToDelete);
}
