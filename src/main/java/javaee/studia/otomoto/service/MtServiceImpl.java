package javaee.studia.otomoto.service;

import javaee.studia.otomoto.commands.MotorcycleCommand;
import javaee.studia.otomoto.converters.MotorcycleCommandToMororcycle;
import javaee.studia.otomoto.converters.MotorcycleToMororcycleCommand;
import javaee.studia.otomoto.model.MotorcycleAdvertisement;
import javaee.studia.otomoto.repository.MtAdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class MtServiceImpl implements MtService {

    private final MtAdRepository mtAdRepository;
    private final MotorcycleCommandToMororcycle motorcycleCommandToMororcycle;
    private final MotorcycleToMororcycleCommand motorcycleToMororcycleCommand;

    public MtServiceImpl(MtAdRepository mtAdRepository, MotorcycleCommandToMororcycle motorcycleCommandToMororcycle, MotorcycleToMororcycleCommand motorcycleToMororcycleCommand) {
        this.mtAdRepository = mtAdRepository;
        this.motorcycleCommandToMororcycle = motorcycleCommandToMororcycle;
        this.motorcycleToMororcycleCommand = motorcycleToMororcycleCommand;
    }

    @Override
    public Set<MotorcycleAdvertisement> getMotorcycles() {
        log.debug("I'm in the service");

        Set<MotorcycleAdvertisement> mtAdvertisementSet = new HashSet<>();
        mtAdRepository.findAll().iterator().forEachRemaining(mtAdvertisementSet::add);

        return mtAdvertisementSet;
    }

    @Override
    public MotorcycleAdvertisement findMotorcycleById(Long l) {

        Optional<MotorcycleAdvertisement> mtOptional = mtAdRepository.findById(l);

        if (!mtOptional.isPresent()) {
            throw new RuntimeException("Not Found!");
        }

        return mtOptional.get();
    }

    @Override
    @Transactional
    public MotorcycleCommand findCommandById(Long l) {
        return motorcycleToMororcycleCommand.convert(findMotorcycleById(l));
    }



    @Override
    @Transactional
    public MotorcycleCommand saveMotorcycleCommand(MotorcycleCommand command) {

        MotorcycleAdvertisement detachedMotorcycleAdvertisement = motorcycleCommandToMororcycle.convert(command);
        MotorcycleAdvertisement savedMotorcycleAdvertisement = mtAdRepository.save(detachedMotorcycleAdvertisement);
        log.debug("Saved MotorcycleId:" + savedMotorcycleAdvertisement.getId());

        return motorcycleToMororcycleCommand.convert(savedMotorcycleAdvertisement);
    }

    @Override
    public void deleteById(Long idToDelete) {
        mtAdRepository.deleteById(idToDelete);
    }
}

