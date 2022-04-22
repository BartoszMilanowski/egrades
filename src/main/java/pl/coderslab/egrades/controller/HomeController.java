package pl.coderslab.egrades.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.FinalGrade;
import pl.coderslab.egrades.entity.Subject;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.login.CurrentUser;
import pl.coderslab.egrades.service.*;

import javax.annotation.security.PermitAll;
import java.text.DecimalFormat;
import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final GradeService gradeService;
    private final SubjectService subjectService;
    private final FinalGradeService finalGradeService;
    private final ClassService classService;

    public HomeController(UserService userService, GradeService gradeService,
                          SubjectService subjectService, FinalGradeService finalGradeService,
                          ClassService classService) {
        this.userService = userService;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
        this.finalGradeService = finalGradeService;
        this.classService = classService;
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

           List<FinalGrade> finalGrades  =finalGradeService.findByStudent(user.getId());
           int sum = 0;
           double avg = 0;
           if (!finalGrades.isEmpty()){
               for (int i = 0; i < finalGrades.size(); i++){
                   sum += finalGrades.get(i).getGradeValue();
               }
               avg = sum / finalGrades.size();
               model.addAttribute("avg", df.format(avg));
           }

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
}
