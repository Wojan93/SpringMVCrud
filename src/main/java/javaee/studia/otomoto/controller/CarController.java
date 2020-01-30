package javaee.studia.otomoto.controller;


import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.model.Car;
import javaee.studia.otomoto.model.UserPrincipal;
import javaee.studia.otomoto.repository.CarRepository;

import javax.validation.Valid;

import javaee.studia.otomoto.repository.UserRepository;
import javaee.studia.otomoto.security.UserPrincipalDetailsService;
import javaee.studia.otomoto.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {

    private CarRepository carRepository;
    //    @Autowired
    private UserRepository userRepository;
    //    @Autowired
    private UserPrincipalDetailsService userDetailsService;
    //    @Autowired
    private UserPrincipal userPrincipal;


//    @Autowired
//    public void setCarRepository(CarRepository carRepository) {
//        this.carRepository = carRepository;
//    }

    @Autowired
    public CarController(CarRepository carRepository, UserRepository userRepository, UserPrincipalDetailsService userDetailsService, UserPrincipal userPrincipal) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.userPrincipal = userPrincipal;
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
        return "addnew";
    }

    @RequestMapping(path = "cars", method = RequestMethod.POST)
    public String saveCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            String username = getUsername();
            car.setSeller(userRepository.findByUsername(username).getId());
            carRepository.save(car);
            return "redirect:/";
        }
    }

    @RequestMapping(path = "/cars", method = RequestMethod.GET)
    public String getAllCars(Model model) {
//        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("cars", carRepository.findByBuyerIsNull());
        return "cars";
    }

    @RequestMapping(path = "/cars/buyed", method = RequestMethod.GET)
    public String getBuyedCars(Model model) {

        String username = getUsername();

        model.addAttribute("cars", carRepository.findByBuyer(userRepository.findByUsername(username).getId()));
        return "cars-buyed";
    }

    @RequestMapping(path = "/cars/forsale", method = RequestMethod.GET)
    public String getForSaleCars(Model model) {

        String username = getUsername();

        model.addAttribute("cars", carRepository.findBySeller(userRepository.findByUsername(username).getId()));
        return "cars-forsale";
    }


    @RequestMapping(path = "/cars/edit/{id}", method = RequestMethod.GET)
    public String editCar(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("car", carRepository.findById(id));
        return "edit";
    }

    @RequestMapping(path = "/cars/buy/{id}", method = RequestMethod.GET)
    public String buyCar(@PathVariable(name = "id") Long id) {
        Car car = carRepository.getOne(id);

        String username = getUsername();

        car.setBuyer(userRepository.findByUsername(username).getId());
        carRepository.save(car);
        return "redirect:/cars";
    }

    @RequestMapping(path = "/cars/delete/{id}", method = RequestMethod.GET)
    public String deleteCar(@PathVariable(name = "id") Long id) {
        carRepository.deleteById(id);
        return "redirect:/cars";
    }

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
