package pl.coderslab.egrades.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.egrades.entity.Grade;
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


    @GetMapping("/grades/{subjectId}")
    public String showGrades(Model model, @AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable Long subjectId){

        User student = currentUser.getUser();
        List<Grade> gradeList = gradeService.findBySubjectAndStudent(subjectId, student.getId());

        model.addAttribute("grades", gradeList);

        return "student/grades";
    }
}
