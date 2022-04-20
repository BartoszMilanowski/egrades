package pl.coderslab.egrades.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.egrades.entity.*;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.service.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final UserService userService;
    private final GradeService gradeService;
    private final SubjectService subjectService;
    private final FinalGradeService finalGradeService;
    private final ClassService classService;

    public TeacherController(UserService userService, GradeService gradeService,
                             SubjectService subjectService, FinalGradeService finalGradeService,
                             ClassService classService) {
        this.userService = userService;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
        this.finalGradeService = finalGradeService;
        this.classService = classService;
    }

    @GetMapping("/class/{classId}/{subjectId}")
    public String showStudents(Model model, @PathVariable Long classId, @PathVariable Long subjectId){

        Optional<Subject> subjectOptional = subjectService.findById(subjectId);
        Subject subject = subjectService.findByOpt(subjectOptional);
        model.addAttribute("subject", subject);
        Optional<Class> optionalClass = classService.findById(classId);
        Class group = classService.findByOpt(optionalClass);
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

        Optional<Subject> subjectOptional = subjectService.findById(subjectId);
        Subject subject = subjectService.findByOpt(subjectOptional);
        Optional<User> userOptional = userService.findById(studentId);
        User student = userService.findByOpt(userOptional);
        Optional<Class> optionalClass = classService.findById(classId);
        Class group = classService.findByOpt(optionalClass);
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
}
