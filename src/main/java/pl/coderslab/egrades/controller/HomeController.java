package pl.coderslab.egrades.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.Grade;
import pl.coderslab.egrades.entity.Subject;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.login.CurrentUser;
import pl.coderslab.egrades.service.ClassService;
import pl.coderslab.egrades.service.GradeService;
import pl.coderslab.egrades.service.SubjectService;
import pl.coderslab.egrades.service.UserService;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final GradeService gradeService;
    private final SubjectService subjectService;
    private final ClassService classService;

    private final BCryptPasswordEncoder passwordEncoder;

    public HomeController(UserService userService, GradeService gradeService,
                          SubjectService subjectService, ClassService classService,
                          BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
        this.classService = classService;
        this.passwordEncoder = passwordEncoder;
    }

    private static final DecimalFormat df = new DecimalFormat("0.00");

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
//Pulpit studenta
        if (user.hasRole("ROLE_STUDENT")){
            List<Subject> subjects = subjectService.findAll();
            model.addAttribute("subjects", subjects);
           List<Grade> finalGrades  = gradeService.findFinalByStudent(user.getId());
           double avg = gradeService.averageFinalGrade(finalGrades);
           model.addAttribute("avg", df.format(avg));

           //Pulpit nauczyciela
        } else if (user.hasRole("ROLE_TEACHER")){
            List<Class> classes = classService.findAll();
            List<Subject> subjects = subjectService.findByTeachers(user);
            model.addAttribute("subjects", subjects);
            model.addAttribute("classes", classes);
        } else if (user.hasRole("ROLE_ADMIN")){
            List<User> users = userService.findAll();
            model.addAttribute("users", users);
        }
        return "dashboard";
    }

    @GetMapping("/user/my-account")
    public String userMyAccount(Model model, @AuthenticationPrincipal CurrentUser currentUser){


        User user = currentUser.getUser();
        model.addAttribute("user", user);

        if (user.hasRole("ROLE_STUDENT")){
            Class group = classService.findByStudent(user);
            model.addAttribute("group", group);
        } else if (user.hasRole("ROLE_TEACHER") || user.hasRole("ROLE_ADMIN")){
            List<Subject> subjects = subjectService.findByTeachers(user);
            model.addAttribute("subjects", subjects);
        }
        return "myAccount";
    }

    @GetMapping("/user/change-password")
    public String changePassForm(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        return "changePass";
    }

    @PostMapping("/user/change-password")
    public String changePass(HttpServletRequest request, @AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        String pass = request.getParameter("password");
        user.setPassword(passwordEncoder.encode(pass));
        userService.save(user);
        return "passChanged";
    }
}
