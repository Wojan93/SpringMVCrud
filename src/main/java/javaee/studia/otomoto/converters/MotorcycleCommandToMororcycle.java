package javaee.studia.otomoto.converters;

import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.commands.MotorcycleCommand;
import javaee.studia.otomoto.model.CarAdvertisement;
import javaee.studia.otomoto.model.MotorcycleAdvertisement;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MotorcycleCommandToMororcycle implements Converter<MotorcycleCommand, MotorcycleAdvertisement> {


    public MotorcycleCommandToMororcycle() {

    }

    @Synchronized
    @Nullable
    @Override
    public MotorcycleAdvertisement convert(MotorcycleCommand source) {
        if (source == null) {
            return null;
        }

        final MotorcycleAdvertisement motorcycleAdvertisement = new MotorcycleAdvertisement();

        motorcycleAdvertisement.setMotorcycleCompany(source.getMotorcycleCompany());
        motorcycleAdvertisement.setModel(source.getModel());
        motorcycleAdvertisement.setTitle(source.getTitle());
        motorcycleAdvertisement.setTextAd(source.getTextAd());
        motorcycleAdvertisement.setVinNumber(source.getVinNumber());
        motorcycleAdvertisement.setColour(source.getColour());
        motorcycleAdvertisement.setPrice(source.getPrice());
        motorcycleAdvertisement.setYear(source.getYear());
        motorcycleAdvertisement.setMotorCapacity(source.getMotorCapacity());
        motorcycleAdvertisement.setKilometers(source.getKilometers());
        motorcycleAdvertisement.setImage(source.getImage());


        return motorcycleAdvertisement;
    }
}

