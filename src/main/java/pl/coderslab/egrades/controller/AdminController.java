package pl.coderslab.egrades.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.Subject;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.service.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final GradeService gradeService;
    private final SubjectService subjectService;
    private final ClassService classService;
    private final RoleService roleService;

    public AdminController(UserService userService, GradeService gradeService,
                           SubjectService subjectService, ClassService classService, RoleService roleService) {
        this.userService = userService;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
        this.classService = classService;
        this.roleService = roleService;
    }

    @GetMapping("/user/{userId}")
    private String userDetails(Model model, @PathVariable Long userId){

        String view = null;
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        if (user.hasRole("ROLE_STUDENT")){
            Class group = classService.findByStudent(user);
            model.addAttribute("group", group);
            view = "admin/studentDetails";
        } else if (user.hasRole("ROLE_TEACHER") || user.hasRole("ROLE_ADMIN")){
            List<Subject> subjectList = subjectService.findByTeachers(user);
            model.addAttribute("subjects", subjectList);
            view = "admin/teacherDetails";
        }
        return view;
    }

    @GetMapping("/user/students")
    private String studentList(Model model){

        List<User> students = userService.findByRoles(roleService.findById(1));
        model.addAttribute("users", students);
        return "admin/userList";
    }

    @GetMapping("/user/teachers")
    private String teachersList(Model model){

        List<User> users = userService.findTeachersAndAdmins();
        model.addAttribute("users", users);
        return "admin/userList";
    }
}
