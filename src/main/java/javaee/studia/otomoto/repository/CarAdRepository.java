package javaee.studia.otomoto.repository;


import javaee.studia.otomoto.model.CarAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarAdRepository extends JpaRepository<CarAdvertisement, Long> {

    List<CarAdvertisement> findBySeller(Long seller);

    List<CarAdvertisement> findByBuyer(Long buyer);

    List<CarAdvertisement> findByBuyerIsNull();


}
