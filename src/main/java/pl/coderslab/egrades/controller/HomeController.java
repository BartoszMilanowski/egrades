package pl.coderslab.egrades.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.egrades.entity.Subject;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.login.CurrentUser;
import pl.coderslab.egrades.service.GradeService;
import pl.coderslab.egrades.service.SubjectService;
import pl.coderslab.egrades.service.UserService;

import javax.annotation.security.PermitAll;
import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final GradeService gradeService;
    private final SubjectService subjectService;

    public HomeController(UserService userService, GradeService gradeService, SubjectService subjectService) {
        this.userService = userService;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
    }

    @PermitAll
    @GetMapping("/")
    String home(){
        return "home";
    }

    @GetMapping("/login")
    String login(){
        return "login";
    }

    @GetMapping("/dashboard")
    String dashboard(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        User user = currentUser.getUser();
        model.addAttribute("user", user);

        if (user.hasRole("ROLE_STUDENT")){
            List<Subject> subjects = subjectService.findAll();
            model.addAttribute("subjects", subjects);
        }
        return "dashboard";
    }



}
