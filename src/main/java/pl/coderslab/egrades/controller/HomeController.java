package pl.coderslab.egrades.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.PermitAll;
import java.time.LocalDateTime;

@Controller
public class HomeController {

    @PermitAll
    @GetMapping("/")
    String home(Model model){
        model.addAttribute("now", LocalDateTime.now());
        return "home";
    }

    @GetMapping("/login")
    String login(){
        return "login";
    }

}
