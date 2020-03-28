package javaee.studia.otomoto.controller;

import javaee.studia.otomoto.model.UserPrincipal;
import javaee.studia.otomoto.repository.CarAdRepository;
import javaee.studia.otomoto.repository.MtAdRepository;
import javaee.studia.otomoto.repository.UserRepository;
import javaee.studia.otomoto.security.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

    private CarAdRepository carAdRepository;
    private MtAdRepository mtAdRepository;
    private UserRepository userRepository;
    private UserPrincipalDetailsService userDetailsService;
    private UserPrincipal userPrincipal;

    @Autowired
    public MainController(CarAdRepository carAdRepository, MtAdRepository mtAdRepository, UserRepository userRepository, UserPrincipalDetailsService userDetailsService, UserPrincipal userPrincipal) {
        this.carAdRepository = carAdRepository;
        this.mtAdRepository = mtAdRepository;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.userPrincipal = userPrincipal;
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

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping(path = "/main/forsale", method = RequestMethod.GET)
    public String getForSaleCars(Model model) {
        String username = getUsername();
        model.addAttribute("cars", carAdRepository.findBySeller(userRepository.findByUsername(username).getId()));
        model.addAttribute("motorcycles", mtAdRepository.findBySeller(userRepository.findByUsername(username).getId()));

        return "forsale";
    }

    @RequestMapping(path = "/main/my-cart", method = RequestMethod.GET)
    public String getBoughtItems(Model model) {
        String username = getUsername();
        model.addAttribute("motorcycles", mtAdRepository.findByBuyer(userRepository.findByUsername(username).getId()));
        model.addAttribute("cars", carAdRepository.findByBuyer(userRepository.findByUsername(username).getId()));

        return "my-cart";
    }


}
