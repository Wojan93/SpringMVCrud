package com.example.java.moto.services;

import com.example.java.moto.model.Advertisement;

import java.util.Set;

public interface AdvertisementService {

    Advertisement findByEmail(String email);

    Advertisement findById(Long id);

    Advertisement save(Advertisement advertisement);

    Set<Advertisement> findAll();
}
