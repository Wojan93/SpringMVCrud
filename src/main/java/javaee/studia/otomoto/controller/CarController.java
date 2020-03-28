package javaee.studia.otomoto.controller;

import javaee.studia.otomoto.model.CarAdvertisement;
import javaee.studia.otomoto.model.UserPrincipal;
import javaee.studia.otomoto.repository.CarAdRepository;
import javax.validation.Valid;
import javaee.studia.otomoto.repository.MtAdRepository;
import javaee.studia.otomoto.repository.UserRepository;
import javaee.studia.otomoto.security.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class CarController {

    private CarAdRepository carAdRepository;
    private MtAdRepository mtAdRepository;
    private UserRepository userRepository;
    private UserPrincipalDetailsService userDetailsService;
    private UserPrincipal userPrincipal;


    @Autowired
    public CarController(CarAdRepository carAdRepository, MtAdRepository mtAdRepository, UserRepository userRepository, UserPrincipalDetailsService userDetailsService, UserPrincipal userPrincipal) {
        this.carAdRepository = carAdRepository;
        this.mtAdRepository = mtAdRepository;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.userPrincipal = userPrincipal;
    }

    @RequestMapping(path = "/main/add-new-car", method = RequestMethod.GET)
    public String createCar(Model model) {
        model.addAttribute("car", new CarAdvertisement());

        return "add-new-car";
    }

    @RequestMapping(path = "cars", method = RequestMethod.POST)
    public String saveCar(@Valid @ModelAttribute("car") CarAdvertisement carAdvertisement, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            String username = getUsername();
            carAdvertisement.setSeller(userRepository.findByUsername(username).getId());
            carAdvertisement.setPhoneNumber(userRepository.findByUsername(username).getPhoneNumber());
            carAdRepository.save(carAdvertisement);

            return "redirect:/";
        }
    }

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String getAllCars(Model model) {
        model.addAttribute("cars", carAdRepository.findByBuyerIsNull());

        return "main/cars";
    }



    @RequestMapping(path = "/main/cars/edit/{id}", method = RequestMethod.GET)
    public String editCar(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("car", carAdRepository.findById(id));

        return "edit";
    }


    @RequestMapping(path = "/main/buy/{id}", method = RequestMethod.GET)
    public String buyCar(@PathVariable(name = "id") Long id) {
        CarAdvertisement carAdvertisement = carAdRepository.getOne(id);
        String username = getUsername();
        carAdvertisement.setBuyer(userRepository.findByUsername(username).getId());
        carAdRepository.save(carAdvertisement);

        return "redirect:/main";
    }

    @RequestMapping(path = "/main/delete/{id}", method = RequestMethod.GET)
    public String deleteCar(@PathVariable(name = "id") Long id) {
        carAdRepository.deleteById(id);

        return "redirect:/main";
    }


    @GetMapping("/main/cars/{id}/show")
    public String showCarId(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("carAdvertisement", carAdRepository.getOne(id));
        return "main/show-a-car";
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
