package javaee.studia.otomoto.repository;

import javaee.studia.otomoto.model.MotorcycleAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MtAdRepository extends JpaRepository<MotorcycleAdvertisement, Long> {

    List<MotorcycleAdvertisement> findBySeller(Long seller);

    List<MotorcycleAdvertisement> findByBuyer(Long buyer);

    List<MotorcycleAdvertisement> findByBuyerIsNull();

}
