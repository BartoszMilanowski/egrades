package pl.coderslab.egrades.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.egrades.entity.Grade;
import pl.coderslab.egrades.entity.Subject;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.login.CurrentUser;
import pl.coderslab.egrades.service.GradeService;
import pl.coderslab.egrades.service.PresenceService;
import pl.coderslab.egrades.service.SubjectService;
import pl.coderslab.egrades.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {


    private final UserService userService;
    private final GradeService gradeService;
    private final SubjectService subjectService;

    private final PresenceService presenceService;


    public StudentController(UserService userService, GradeService gradeService,
                             SubjectService subjectService, PresenceService presenceService) {
        this.userService = userService;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
        this.presenceService = presenceService;
    }

    @GetMapping("/grades/{subjectName}")
    public String showGrades(Model model, @AuthenticationPrincipal CurrentUser currentUser,
                              @PathVariable String subjectName){

        User student = currentUser.getUser();
        Subject subject = subjectService.findByName(subjectName);
        List<Grade> gradeList = gradeService.findBySubjectAndStudent(subject.getId(), student.getId());
        Grade finalGrade = gradeService.findFinalBySubjectAndStudent(subject.getId(), student.getId());
        double frequency = presenceService.avgPresence(subject, student);
        model.addAttribute("grades", gradeList);
        model.addAttribute("subjectName", subjectName);
        if (!Double.isNaN(frequency)){
            model.addAttribute("frequency", frequency + "%");
        } else {
            model.addAttribute("frequency", "-");
        }
        if (finalGrade != null){
            model.addAttribute("finalGrade", finalGrade.getGradeValue());
        } else {
            model.addAttribute("finalGrade", "-");
        }
        return "student/grades";
    }
}
