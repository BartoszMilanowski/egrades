package pl.coderslab.egrades.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.Role;
import pl.coderslab.egrades.entity.Subject;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.service.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final GradeService gradeService;
    private final SubjectService subjectService;
    private final ClassService classService;
    private final RoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder;

   private final EmailSender emailSender;


    public AdminController(UserService userService, GradeService gradeService, SubjectService subjectService,
                           ClassService classService, RoleService roleService, BCryptPasswordEncoder passwordEncoder,
                           EmailSender emailSender) {
        this.userService = userService;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
        this.classService = classService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.emailSender = emailSender;
    }

    @ModelAttribute(name = "classes")
    protected List<Class> getClasses(){
        return classService.findAll();
    }

    @ModelAttribute(name = "subjects")
    protected List<Subject> getSubjects(){
        return subjectService.findAll();
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

    @GetMapping("/add-user/{roleName}")
    public String addUserForm(Model model, @PathVariable String roleName){

        String view = new String();
        Role role = new Role();
        if (roleName.equals("student")){
            role = roleService.findByName("ROLE_STUDENT");
            view = "admin/addStudent";
        } else if (roleName.equals("teacher")){
            role = roleService.findByName("ROLE_TEACHER");
            view = "admin/addTeacher";
        } else if (roleName.equals("admin")){
            role = roleService.findByName("ROLE_ADMIN");
            view = "admin/addTeacher";
        }

        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("role", role);

        return view;
    }

    @PostMapping("/add-user/{roleName}")
    public String addUser(User user, @PathVariable String roleName){

        String redirect = new String();

        Role role = new Role();
        if (roleName.equals("student")){
            role = roleService.findByName("ROLE_STUDENT");
            redirect = "redirect:/admin/user/students";
        } else if (roleName.equals("teacher")){
            role = roleService.findByName("ROLE_TEACHER");
            redirect = "redirect:/admin/user/teachers";
        } else if (roleName.equals("admin")){
            role = roleService.findByName("ROLE_ADMIN");
            redirect = "redirect:/admin/user/teachers";
        }

        String password = PasswordGenerator.generateStrongPassword();
        System.out.println(password);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(1);
        userService.save(user);
        emailSender.sendEmail(user, password);

        return redirect;
    }
}
