package javaee.studia.otomoto.converters;

import javaee.studia.otomoto.commands.MotorcycleCommand;
import javaee.studia.otomoto.model.MotorcycleAdvertisement;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MotorcycleToMororcycleCommand implements Converter<MotorcycleAdvertisement, MotorcycleCommand> {


    public MotorcycleToMororcycleCommand() {

    }


    @Synchronized
    @Nullable
    @Override
    public MotorcycleCommand convert(MotorcycleAdvertisement source) {
        if (source == null) {
            return null;
        }

        final MotorcycleCommand command = new MotorcycleCommand();

        command.setId(source.getId());
        command.setMotorcycleCompany(source.getMotorcycleCompany());
        command.setModel(source.getModel());
        command.setTitle(source.getTitle());
        command.setTextAd(source.getTextAd());
        command.setVinNumber(source.getVinNumber());
        command.setColour(source.getColour());
        command.setPrice(source.getPrice());
        command.setYear(source.getYear());
        command.setMotorCapacity(source.getMotorCapacity());
        command.setKilometers(source.getKilometers());
        command.setImage(source.getImage());
        command.setPhoneNumber(source.getPhoneNumber());

        return command;
    }
}