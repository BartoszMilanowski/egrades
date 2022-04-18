package pl.coderslab.egrades.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.egrades.entity.Grade;
import pl.coderslab.egrades.entity.Subject;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.login.CurrentUser;
import pl.coderslab.egrades.service.GradeService;
import pl.coderslab.egrades.service.SubjectService;
import pl.coderslab.egrades.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final UserService userService;
    private final GradeService gradeService;
    private final SubjectService subjectService;

    public StudentController(UserService userService, GradeService gradeService, SubjectService subjectService) {
        this.userService = userService;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
    }


    @GetMapping("/grades/{subjectName}")
    public String showGrades(Model model, @AuthenticationPrincipal CurrentUser currentUser,
                             @ModelAttribute @PathVariable String subjectName){

        User student = currentUser.getUser();
        Subject subject = subjectService.findByName(subjectName);
        List<Grade> gradeList = gradeService.findBySubjectAndStudent(subject.getId(), student.getId());

        model.addAttribute("grades", gradeList);

        return "student/grades";
    }
}
