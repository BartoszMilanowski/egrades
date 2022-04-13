package pl.coderslab.egrades.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.egrades.service.UserService;

import javax.annotation.security.PermitAll;
import java.time.LocalDateTime;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

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
