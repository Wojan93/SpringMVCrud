package javaee.studia.otomoto.repository;


import javaee.studia.otomoto.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findBySeller(Long seller);

    List<Car> findByBuyer(Long buyer);

    List<Car> findByBuyerIsNull();


}
