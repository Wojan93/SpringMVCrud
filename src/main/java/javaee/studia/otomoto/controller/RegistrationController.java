package javaee.studia.otomoto.controller;


import javaee.studia.otomoto.model.User;
import javaee.studia.otomoto.repository.UserRepository;
import javaee.studia.otomoto.security.RegistrationForm;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    // Remove white spaces in form
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("registerForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("registerForm") RegistrationForm form, BindingResult bindingResult, Model model) {

        String userName = form.getUsername();

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (!form.getPassword().equals(form.getPasswordConfirmation())) {
            model.addAttribute("registrationErrorDiffPass", "Passwords must be the same");

            return "registration";
        }

        // check the database if user already exists
        User existingUser = userRepo.findByUsername(userName);
        if (existingUser != null) {
            model.addAttribute("regUser", new RegistrationForm());
            model.addAttribute("registrationError", "User name already exists. Choose another.");

            return "registration";
        }

        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";

    }



//    @GetMapping
//    public String registerForm() {
//        return "registration";
//    }
//
//    @PostMapping
//    public String processRegistration(RegistrationForm form) {
//        userRepo.save(form.toUser(passwordEncoder));
//        return "redirect:/login";
//    }

}
