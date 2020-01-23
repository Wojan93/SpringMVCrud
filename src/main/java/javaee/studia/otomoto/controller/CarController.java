package javaee.studia.otomoto.controller;


import javaee.studia.otomoto.model.Car;
import javaee.studia.otomoto.repository.CarRepository;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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


    // Remove white spaces in form
 	@InitBinder
 	public void initBinder(WebDataBinder webDataBinder) {
 		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
 		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
 	}

    @RequestMapping(path = "/cars/add", method = RequestMethod.GET)
    public String createProduct(Model model) {
        model.addAttribute("car", new Car());
        return "edit";
    }

    @RequestMapping(path = "cars", method = RequestMethod.POST)
    public String saveCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult) {
    	
    	if (bindingResult.hasErrors()) {
    		return "edit";
    	}else {
    		carRepository.save(car);
    		return "redirect:/";
    	} 
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
