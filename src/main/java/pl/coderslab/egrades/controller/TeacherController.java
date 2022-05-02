package pl.coderslab.egrades.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.*;
import pl.coderslab.egrades.login.CurrentUser;
import pl.coderslab.egrades.service.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final UserService userService;
    private final GradeService gradeService;
    private final SubjectService subjectService;
    private final ClassService classService;

    private final PresenceService presenceService;


    public TeacherController(UserService userService, GradeService gradeService,
                             SubjectService subjectService, ClassService classService,
                             PresenceService presenceService) {
        this.userService = userService;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
        this.classService = classService;
        this.presenceService = presenceService;
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
        Grade finalGrade = gradeService.findFinalBySubjectAndStudent(subjectId, studentId);

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
    public String addGradeForm(Model model, @PathVariable Long subjectId, @PathVariable Long studentId){
        User student = userService.findById(studentId);
        Subject subject = subjectService.findById(subjectId);
        Class group = classService.findByStudent(student);
        model.addAttribute("grade",new Grade());
        model.addAttribute("student", student);
        model.addAttribute("subject", subject);
        model.addAttribute("group", group);
        return "teacher/addGradeForm";
    }

    @PostMapping("/grade/add")
    public String addGrade(Grade grade, HttpServletRequest request,
                           @AuthenticationPrincipal CurrentUser currentUser){
        grade.setDateTime(LocalDate.now());
        Long studentId = Long.parseLong(request.getParameter("student"));
        Long subjectId = Long.parseLong(request.getParameter("subject"));
        User teacher = currentUser.getUser();
        grade.setSubject(subjectService.findById(subjectId));
        grade.setTeacher(teacher);
        grade.setStudent(userService.findById(studentId));
        Class group = classService.findByStudent(userService.findById(studentId));

            gradeService.save(grade);
            return "redirect:/teacher/class/" + group.getId() + "/" + subjectId + "/" + studentId;
    }

    @GetMapping("/final-grade/{subjectId}/{studentId}")
    public String finalGradeForm(Model model, @PathVariable Long subjectId,
                                 @PathVariable Long studentId){
        User student = userService.findById(studentId);
        Subject subject = subjectService.findById(subjectId);
        Class group = classService.findByStudent(student);
        model.addAttribute("student", student);
        model.addAttribute("subject", subject);
        model.addAttribute("group", group);

        Grade finalGrade = gradeService.findFinalBySubjectAndStudent(subjectId, studentId);
        if (finalGrade == null){
            model.addAttribute("finalGrade", new Grade());
            return "teacher/finalGradeForm";
        } else {
            model.addAttribute("finalGrade", finalGrade);
            return "teacher/finalGradeEdit";
        }
    }

    @PostMapping("/final-grade/add")
    public String addFinalGrade(Grade finalGrade, HttpServletRequest request,
                                @AuthenticationPrincipal CurrentUser currentUser){
        Long studentId = Long.parseLong(request.getParameter("student"));
        Long subjectId = Long.parseLong(request.getParameter("subject"));
        User teacher = currentUser.getUser();
        finalGrade.setTeacher(teacher);
        finalGrade.setStudent(userService.findById(studentId));
        finalGrade.setSubject(subjectService.findById(subjectId));
        finalGrade.setFinal(true);
        finalGrade.setDateTime(LocalDate.now());
        Class group = classService.findByStudent(userService.findById(studentId));

        gradeService.save(finalGrade);
        return "redirect:/teacher/class/" + group.getId() + "/" + subjectId + "/" + studentId;
    }

    @PostMapping("/final-grade/edit")
    public String editFinalGrade(Grade finalGrade, HttpServletRequest request,
                                @AuthenticationPrincipal CurrentUser currentUser){
        Long studentId = Long.parseLong(request.getParameter("student"));
        Long subjectId = Long.parseLong(request.getParameter("subject"));
        User teacher = currentUser.getUser();
        finalGrade.setTeacher(teacher);
        finalGrade.setStudent(userService.findById(studentId));
        finalGrade.setSubject(subjectService.findById(subjectId));
        finalGrade.setFinal(true);
        finalGrade.setDateTime(LocalDate.now());
        Class group = classService.findByStudent(userService.findById(studentId));
        Grade prevFinalGrade = gradeService.findFinalBySubjectAndStudent(subjectId, studentId);
        gradeService.deleteById(prevFinalGrade.getId());

        gradeService.update(finalGrade);
        return "redirect:/teacher/class/" + group.getId() + "/" + subjectId + "/" + studentId;
    }

    @GetMapping("/grade/delete/{gradeId}")
    public String deleteGrade(@PathVariable Long gradeId){

        Long studentId = userService.findStudentByGradeId(gradeId).getId();
        Long subjectId = subjectService.findSubjectByGradeId(gradeId).getId();
        Long groupId = classService.findByStudent(userService.findById(studentId)).getId();

        gradeService.deleteById(gradeId);

        return "redirect:/teacher/class/" + groupId + "/" + subjectId + "/" + studentId;
    }

    @GetMapping("/grade/edit/{gradeId}")
    public String editGradeForm(Model model, @PathVariable Long gradeId){

        User student = userService.findStudentByGradeId(gradeId);
        Subject subject = subjectService.findSubjectByGradeId(gradeId);
        Class group = classService.findByStudent(student);
        Grade grade = gradeService.findById(gradeId);
        model.addAttribute("student", student);
        model.addAttribute("subject", subject);
        model.addAttribute("group", group);
        model.addAttribute("grade", grade);

        return "teacher/editGradeForm";
    }

    @PostMapping("/grade/edit/{gradeId}")
    public String editGrade(Grade grade, @PathVariable Long gradeId,HttpServletRequest request,
                            @AuthenticationPrincipal CurrentUser currentUser){
        Long studentId = Long.parseLong(request.getParameter("student"));
        Long subjectId = Long.parseLong(request.getParameter("subject"));
        User teacher = currentUser.getUser();
        grade.setDateTime(LocalDate.now());
        grade.setSubject(subjectService.findById(subjectId));
        grade.setTeacher(teacher);
        grade.setStudent(userService.findById(studentId));
        Long groupId = classService.findByStudent(userService.findById(studentId)).getId();

        gradeService.deleteById(gradeId);
        gradeService.update(grade);
        return "redirect:/teacher/class/" + groupId + "/" + subjectId + "/" + studentId;
    }

    @GetMapping("/presence/class/{classId}/{subjectId}")
    public String showPresenceList(Model model, @PathVariable Long classId,
                                   @PathVariable Long subjectId, @AuthenticationPrincipal CurrentUser currentUser){

        Class group = classService.findById(classId);
        Subject subject = subjectService.findById(subjectId);
        User teacher = currentUser.getUser();
        List<Presence> presences = presenceService.findBySubjectAndClass(subject, group);
        model.addAttribute("group", group);
        model.addAttribute("subject", subject);
        model.addAttribute("teacher", teacher);
        model.addAttribute("presences", presences);
        return "teacher/presencesList";
    }
}