package pl.coderslab.egrades.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.*;
import pl.coderslab.egrades.login.CurrentUser;
import pl.coderslab.egrades.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final UserService userService;
    private final GradeService gradeService;
    private final SubjectService subjectService;
    private final FinalGradeService finalGradeService;
    private final ClassService classService;

    private final Validator validator;


    public TeacherController(UserService userService, GradeService gradeService, SubjectService subjectService,
                             FinalGradeService finalGradeService, ClassService classService, Validator validator) {
        this.userService = userService;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
        this.finalGradeService = finalGradeService;
        this.classService = classService;
        this.validator = validator;
    }

    @ModelAttribute("gradeList")
    public List<Double> gradeList(){
        return Arrays.asList(0.0, 1.0, 1.5, 1.75, 2.0, 2.5, 2.75, 3.0, 3.5, 3.75,
                4.0, 4.5, 4.75, 5.0, 5.5, 6.0);
    }

    @GetMapping("/class/{classId}/{subjectId}")
    public String showStudents(Model model, @PathVariable Long classId, @PathVariable Long subjectId){

        Subject subject = subjectService.findById(subjectId);
        model.addAttribute("subject", subject);
        Class group = classService.findById(classId);
        model.addAttribute("group", group);
        List<User> students = userService.findStudentByClasses(group);

        if (!students.isEmpty()){
            model.addAttribute("students", students);
        }
        return "teacher/classList";
    }

    @GetMapping("/class/{classId}/{subjectId}/{studentId}")
    public String showGrades(Model model, @PathVariable Long classId,
                             @PathVariable Long subjectId, @PathVariable Long studentId){


        Subject subject = subjectService.findById(subjectId);
        User student = userService.findById(studentId);
        Class group = classService.findById(classId);
        FinalGrade finalGrade = finalGradeService.findBySubjectAndStudent(subject.getId(), student.getId());

        model.addAttribute("group", group);
        model.addAttribute("subject", subject);
        model.addAttribute("student", student);

        if (finalGrade != null){
            model.addAttribute("finalGrade", finalGrade.getGradeValue());
        }

        List<Grade> grades = gradeService.findBySubjectAndStudent(subjectId, studentId);
        model.addAttribute("grades", grades);
        return "teacher/studentGrades";
    }
    @GetMapping("/grade/add/{subjectId}/{studentId}")
    public String addGradeForm(Model model, @PathVariable Long subjectId, @PathVariable Long studentId,
                                 @AuthenticationPrincipal CurrentUser currentUser){
        User teacher = currentUser.getUser();
        User student = userService.findById(studentId);
        Subject subject = subjectService.findById(subjectId);
        model.addAttribute("grade",new Grade());
        model.addAttribute("student", student);
        model.addAttribute("subject", subject);
        return "teacher/addGradeForm";
    }

    @PostMapping("/grade/add")
    public String addGrade(Grade grade, HttpServletRequest request,
                           @AuthenticationPrincipal CurrentUser currentUser){
        grade.setDateTime(LocalDateTime.now());
        Long studentId = Long.parseLong(request.getParameter("student"));
        Long subjectId = Long.parseLong(request.getParameter("subject"));
        User teacher = currentUser.getUser();
        grade.setSubject(subjectService.findById(subjectId));
        grade.setTeacher(teacher);
        grade.setStudent(userService.findById(studentId));
        Class group = classService.findByStudent(userService.findById(studentId));

        Set<ConstraintViolation<Grade>> violations = validator.validate(grade);

        if (!violations.isEmpty()){
            return "redirect:/teacher/grade/add/" + subjectId + "/" + studentId;
        } else {
            gradeService.save(grade);
            return "redirect:/teacher/class/" + group.getId() + "/" + subjectId + "/" + studentId;
        }


    }
}