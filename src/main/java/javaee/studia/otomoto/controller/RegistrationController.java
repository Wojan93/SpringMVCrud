package javaee.studia.otomoto.controller;


import javaee.studia.otomoto.email.EmailSender;
import javaee.studia.otomoto.model.User;
import javaee.studia.otomoto.repository.UserRepository;
import javaee.studia.otomoto.security.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.validation.Valid;

/**
 *
 * @author Mateusz Wilk
 */

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder, EmailSender emailSender, TemplateEngine templateEngine) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
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

        Context context = new Context();
        context.setVariable("header", "Utworzenie nowego konta");
        context.setVariable("title", "Gratulujemy, konto w Moto Market Application zostało utworzone");
        context.setVariable("description", "Życzymy udanych zakupów");
        String body = templateEngine.process("email", context);
        emailSender.sendEmail(form.getEmail(), "Moto Market Application - utworzenie konta", body);

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
