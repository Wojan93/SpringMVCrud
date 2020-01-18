package javaee.studia.otomoto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

    @RequestMapping(path = "/logout")
    public String logout() {
        return "logout";
    }


    @RequestMapping(path = "/login")
    public String login() {
        return "login";
    }

}
