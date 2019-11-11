package com.example.java.moto.services;

import com.example.java.moto.model.Car;

import java.util.Set;

public interface CarService {

    Car findByCompany(String company);
    Car findByModel(String model);
    Car findByVin(String vin);
    Car findById(Long id);

    Car save(Car car);

    Set<Car> findAll();
}
