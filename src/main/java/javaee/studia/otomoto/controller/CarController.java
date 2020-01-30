package javaee.studia.otomoto.controller;


import javaee.studia.otomoto.commands.CarCommand;
import javaee.studia.otomoto.repository.CarRepository;
import javaee.studia.otomoto.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class CarController {

    private final CarService carService;
    private CarRepository carRepository;

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Remove white spaces in form
 	@InitBinder
 	public void initBinder(WebDataBinder webDataBinder) {
 		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
 		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
 	}

    @GetMapping("/car/show/{id}")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("car", carService.findById(Long.valueOf(id)));
        return "car";
    }

    @GetMapping("car/add")
    public String newCar(Model model){
        model.addAttribute("car", new CarCommand());

        return "car";
    }

    @GetMapping("car/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("car", carService.findCommandById(Long.valueOf(id)));
        return  "car";
    }


    @RequestMapping(path = "/car", method = RequestMethod.GET)
    public String getAllCars(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return "car";
    }

    @PostMapping("car")
    public String saveOrUpdate(@ModelAttribute CarCommand command){
        CarCommand savedCommand = carService.saveCarCommand(command);

        return "redirect:/car/show/" + savedCommand.getId();
    }

    @GetMapping("car/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        carService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
