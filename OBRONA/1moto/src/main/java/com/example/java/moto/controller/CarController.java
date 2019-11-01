package com.example.java.moto.controller;

import com.example.java.moto.model.Car;
import com.example.java.moto.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CarController {

    private CarRepository carRepository;

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/cars/add", method = RequestMethod.GET)
    public String createProduct(Model model) {
        model.addAttribute("car", new Car());
        return "edit";
    }

    @RequestMapping(path = "cars", method = RequestMethod.POST)
    public String saveCar(Car car) {
        carRepository.save(car);
        return "redirect:/";
    }

    @RequestMapping(path = "/cars", method = RequestMethod.GET)
    public String getAllCars(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return "cars";
    }

    @RequestMapping(path = "/cars/edit/{id}", method = RequestMethod.GET)
    public String editCar(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("car", carRepository.findById(id));
        return "edit";
    }

    @RequestMapping(path = "/cars/delete/{id}", method = RequestMethod.GET)
    public String deleteCar(@PathVariable(name = "id") String id) {
        carRepository.deleteById(id);
        return "redirect:/cars";
    }
}
