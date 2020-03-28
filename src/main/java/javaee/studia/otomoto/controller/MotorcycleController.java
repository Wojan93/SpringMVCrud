package javaee.studia.otomoto.controller;

import javaee.studia.otomoto.model.CarAdvertisement;
import javaee.studia.otomoto.model.MotorcycleAdvertisement;
import javaee.studia.otomoto.repository.MtAdRepository;
import javaee.studia.otomoto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MotorcycleController {

    private MtAdRepository mtAdRepository;
    private UserRepository userRepository;

    @Autowired
    public MotorcycleController(MtAdRepository mtAdRepository, UserRepository userRepository) {
        this.mtAdRepository = mtAdRepository;
        this.userRepository = userRepository;
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

    @RequestMapping(path = "/main/motorcycles", method = RequestMethod.POST)
    public String saveMotorcycle(@Valid @ModelAttribute("motorcycle") MotorcycleAdvertisement motorcycleAdvertisement, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            String username = getUsername();

            motorcycleAdvertisement.setSeller(userRepository.findByUsername(username).getId());
            motorcycleAdvertisement.setPhoneNumber(userRepository.findByUsername(username).getPhoneNumber());
            mtAdRepository.save(motorcycleAdvertisement);

            return "redirect:/";
        }
    }

    @RequestMapping(path = "/main/motorcycles", method = RequestMethod.GET)
    public String getAllMotorcycles(Model model) {
        model.addAttribute("motorcycles", mtAdRepository.findByBuyerIsNull());

        return "/main/motorcycles";
    }

    @RequestMapping(path = "/main/add-new-motorcycle", method = RequestMethod.GET)
    public String createMotorcycle(Model model) {
        model.addAttribute("motorcycle", new CarAdvertisement());

        return "add-new-motorcycle";
    }


  @RequestMapping(path = "/main/motorcycles/main/forsale", method = RequestMethod.GET)
    public String getForSaleMotorcycles(Model model) {
        String username = getUsername();

        model.addAttribute("motorcycles", mtAdRepository.findBySeller(userRepository.findByUsername(username).getId()));

        return "forsale";
    }


    @RequestMapping(path = "/main/motorcycles/edit/{id}", method = RequestMethod.GET)
    public String editMotorcycle(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("motorcycle", mtAdRepository.findById(id));

        return "edit";
    }


    @RequestMapping(path = "/main/motorcycles/buy/{id}", method = RequestMethod.GET)
    public String buyMotorcycle(@PathVariable(name = "id") Long id) {
        MotorcycleAdvertisement motorcycleAdvertisement = mtAdRepository.getOne(id);
        String username = getUsername();
        motorcycleAdvertisement.setBuyer(userRepository.findByUsername(username).getId());
        mtAdRepository.save(motorcycleAdvertisement);

        return "redirect:/main";
    }

    @RequestMapping(path = "/main/motorcycles/delete/{id}", method = RequestMethod.GET)
    public String deleteMotorcycle(@PathVariable(name = "id") Long id) {
        mtAdRepository.deleteById(id);

        return "redirect:/main";
    }

    @GetMapping("/main/motorcycles/{id}/show")
    public String showCarId(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("motorcycleAdvertisement", mtAdRepository.getOne(id));
        return "main/show-a-motorcycle";
    }

}
