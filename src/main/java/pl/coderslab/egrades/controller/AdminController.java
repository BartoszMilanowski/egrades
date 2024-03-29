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

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final SubjectService subjectService;
    private final ClassService classService;
    private final RoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder;

   private final EmailSender emailSender;


    public AdminController(UserService userService, SubjectService subjectService,
                           ClassService classService, RoleService roleService,
                           BCryptPasswordEncoder passwordEncoder, EmailSender emailSender) {
        this.userService = userService;
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

    @ModelAttribute(name = "allTeachers")
    private List<User> getAllTeachers(){
        return userService.findTeachersAndAdmins();
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
        }
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("role", role);

        return view;
    }

    @PostMapping("/add-user/{roleName}")
    public String addUser(User user, @PathVariable String roleName, HttpServletRequest request){

        String redirect = new String();

        String password = PasswordGenerator.generateStrongPassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(1);
        userService.save(user);
        user = userService.findById(user.getId());
        Role role = new Role();

        if (roleName.equals("student")){
            role = roleService.findByName("ROLE_STUDENT");
            redirect = "redirect:/dashboard#students";
        } else if (roleName.equals("teacher")){
            userService.save(user);
            redirect = "redirect:/dashboard#teachers";
            String[] checkedSubjects = request.getParameterValues("subject");
            for (String s : checkedSubjects){
                Subject subject = subjectService.findById(Long.parseLong(s));
                subjectService.addTeacherToSubject(subject, user);
            }
            String admin = request.getParameter("admin");
            if (admin != null){
                role = roleService.findByName("ROLE_ADMIN");
            } else {
                role = roleService.findByName("ROLE_TEACHER");
            }
        }
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRoles(roleSet);
        userService.update(user);
        emailSender.sendEmail(user, password);

        return redirect;
    }

    @GetMapping("/edit-user/{roleName}/{userId}")
    public String editUserForm(Model model, @PathVariable String roleName, @PathVariable Long userId){

        String view = new String();
        User user = userService.findById(userId);
        model.addAttribute("user", user);

        if (roleName.equals("student")){
            Class group = classService.findByStudent(user);
            model.addAttribute("group", group);
            List<Class> classes = classService.findOtherClasses(group);
            model.addAttribute("otherClasses", classes);
            view = "admin/editStudent";
        } else if (roleName.equals("teacher")){
            List<Subject> subjects = subjectService.findByTeachers(user);
            List<Subject> otherSubjects = subjectService.showOtherSubjects(user);
            model.addAttribute("tSubjects", subjects);
            model.addAttribute("oSubjects", otherSubjects);
            if (user.hasRole("ROLE_ADMIN")){
                model.addAttribute("admin", "admin");
            }
            view = "admin/editTeacher";
        }

        return view;
    }

    @PostMapping("/edit-user/{roleName}/{userId}")
    public String editUser(User user, @PathVariable String roleName, @PathVariable Long userId,
                           HttpServletRequest request){

        String redirect = new String();
        Role role;
        if (roleName.equals("student")){
            role = roleService.findByName("ROLE_STUDENT");
            Set<Role> roles = user.getRoles();
            roles.add(role);
            user.setRoles(roles);
            user.setId(userId);
            userService.update(user);
            redirect = "redirect:/dashboard#students";
        } else if (roleName.equals("teacher")){
            redirect = "redirect:/dashboard#teachers";
            String admin = request.getParameter("admin");
            Set<Role> roles = user.getRoles();
            if (admin != null){
                role = roleService.findByName("ROLE_ADMIN");
            } else {
                role = roleService.findByName("ROLE_TEACHER");
            }
            roles.add(role);
            user.setRoles(roles);
            user.setId(userId);
            userService.update(user);
            String[] checkedSubjects = request.getParameterValues("subject");
            for (String s : checkedSubjects){
                Subject subject = subjectService.findById(Long.parseLong(s));
                subjectService.addTeacherToSubject(subject, user);
            }
        }
        return redirect;
    }

    @GetMapping("/subject/{subjectId}")
    public String subjectDetails(Model model, @PathVariable Long subjectId){
        Subject subject = subjectService.findById(subjectId);
        List<User> teachers = subjectService.findTeachers(subject);
        model.addAttribute("subject", subject);
        model.addAttribute("teachers", teachers);
        return "admin/subjectDetails";
    }

    @GetMapping("/subject/remove-teacher/{subjectId}/{teacherId}")
    public String removeTeacherFromSubject(@PathVariable Long subjectId, @PathVariable Long teacherId){
        subjectService.removeTeacherFromSubject(subjectId, teacherId);
        return "redirect:/admin/subject/" + subjectId;
    }

    @GetMapping("/add-subject")
    public String addSubjectForm(Model model){
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        return "admin/addSubject";
    }

    @PostMapping("/add-subject")
    public String addSubject(Subject subject, HttpServletRequest request){

        subjectService.save(subject);
        String[] teachers = request.getParameterValues("teacher");
        for (String t : teachers) {
            User teacher = userService.findById(Long.parseLong(t));
            subjectService.addTeacherToSubject(subject, teacher);
        }
        return "redirect:/admin/subject/" + subject.getId();
    }

    @GetMapping("/class/{classId}")
    public String showClass(Model model, @PathVariable Long classId){
        Class group = classService.findById(classId);
        List<User> students = classService.findStudents(classId);
        model.addAttribute("group", group);
        model.addAttribute("students", students);
        return "admin/classDetails";
    }

    @GetMapping("/add-class")
    public String addClassForm(Model model){
        Class group = new Class();
        model.addAttribute("group", group);
        return "admin/addClass";
    }

    @PostMapping("/add-class")
    public String addClass(Class group){
        classService.save(group);
        return "redirect:/dashboard#classes";
    }

    @GetMapping("/edit-class/{classId}")
    public String editClassForm(Model model, @PathVariable Long classId){
        Class group = classService.findById(classId);
        List<User> otherTeachers = userService.showOtherTeachers(group.getSupervisingTeacher());
        model.addAttribute("otherTeachers", otherTeachers);
        model.addAttribute("group", group);
        return "admin/editClass";
    }

    @PostMapping("/edit-class/{classId}")
    public String editClass(Class group){
        classService.update(group);
        return "redirect:/admin/class/" + group.getId();
    }
}
