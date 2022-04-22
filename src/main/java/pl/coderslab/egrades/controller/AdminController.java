package pl.coderslab.egrades.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.Role;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.service.*;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final GradeService gradeService;
    private final SubjectService subjectService;
    private final FinalGradeService finalGradeService;
    private final ClassService classService;

    public AdminController(UserService userService, GradeService gradeService,
                           SubjectService subjectService, FinalGradeService finalGradeService,
                           ClassService classService) {
        this.userService = userService;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
        this.finalGradeService = finalGradeService;
        this.classService = classService;
    }

    @GetMapping("/user/{userId}")
    private String userDetails(Model model, @PathVariable Long userId){
        User user = userService.findById(userId);
        Set<Role> roleSet = user.getRoles();
        model.addAttribute("role", roleSet);
        model.addAttribute("user", user);
        if (user.hasRole("ROLE_STUDENT")){
            Class group = classService.findByStudent(user);
            model.addAttribute("group", group);
        }
        return "admin/userDetails";
    }
}
