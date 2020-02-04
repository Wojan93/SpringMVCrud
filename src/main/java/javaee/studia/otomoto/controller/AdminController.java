package javaee.studia.otomoto.controller;


import javaee.studia.otomoto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserRepository userRepository;

    @Autowired
    public void setCarRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String admin() {
        return "admin";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

//    @GetMapping("/delete")
//    public String deleteUser(@RequestParam("id") Long id) {
//        userRepository.deleteById(id);
//        return "redirect:/admin/users";
//    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }

}