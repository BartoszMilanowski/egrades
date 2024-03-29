package pl.coderslab.egrades.controller;

import org.apache.commons.math3.util.Precision;
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
import pl.coderslab.egrades.DTO.StudentAtList;
import pl.coderslab.egrades.service.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    private static final DecimalFormat df = new DecimalFormat("0.00");



    @PostMapping("/select-class")
    public String selectClass(HttpServletRequest request){
        Long classId = Long.parseLong(request.getParameter("group"));
        String subject = request.getParameter("subject");
        String redirect = new String();
        if (subject.equals("avg")){
            redirect =  "redirect:/teacher/class/avg/" + classId;
        } else {
            Long subjectId = Long.parseLong(subject);
        redirect = "redirect:/teacher/class/" + classId + "/" + subjectId;
        }
        return redirect;
    }

    @GetMapping("/class/{classId}/{subjectId}")
    public String showStudents(Model model, @PathVariable Long classId, @PathVariable Long subjectId){

        Subject subject = subjectService.findById(subjectId);
        model.addAttribute("subject", subject);
        Class group = classService.findById(classId);
        model.addAttribute("group", group);
        List<User> students = userService.findStudentByClasses(group);
        List<StudentAtList> studentAtLists = new ArrayList<>();

        if (!students.isEmpty()){
            for (User s : students){
                String grades = gradeService.gradesToList(subject, s);
                double freq = presenceService.avgPresence(subject, s);
                StudentAtList student = new StudentAtList(s, grades);
                if (!Double.isNaN(freq)){
                    student.setFrequency(Precision.round(freq,2));
                }
                studentAtLists.add(student);
            }
        }

        model.addAttribute("students", studentAtLists);
        return "teacher/classList";
    }

    @GetMapping("/class/{classId}/{subjectId}/{studentId}")
    public String showGrades(Model model, @PathVariable Long classId,
                             @PathVariable Long subjectId, @PathVariable Long studentId){


        Subject subject = subjectService.findById(subjectId);
        User student = userService.findById(studentId);
        Class group = classService.findById(classId);
        Grade finalGrade = gradeService.findFinalBySubjectAndStudent(subjectId, studentId);
        double avgGrade = gradeService.avgGrade(subject, student);

        model.addAttribute("group", group);
        model.addAttribute("subject", subject);
        model.addAttribute("student", student);

        if (!Double.isNaN(avgGrade)){
            model.addAttribute("avgGrade", Precision.round(avgGrade,2));
        } else {
            model.addAttribute("avgGrade", " - ");
        }

        if (finalGrade != null){
            model.addAttribute("finalGrade", finalGrade.getGradeValue());
        } else {
            model.addAttribute("finalGrade", " - ");
        }

        List<Grade> grades = gradeService.findBySubjectAndStudent(subjectId, studentId);
        model.addAttribute("grades", grades);
        return "teacher/studentGrades";
    }

    @GetMapping("/class/avg/{classId}")
    public String showClassAvg(Model model, @PathVariable Long classId){

        Class group = classService.findById(classId);
        List<User> students = userService.findStudentByClasses(group);
        List<StudentAtList> studentAtLists = new ArrayList<>();
        for (User s : students){
            List<Grade> finalGrades = gradeService.findFinalByStudent(s.getId());
            double avg = gradeService.averageFinalGrade(finalGrades);
            double totalFrequency = presenceService.totalFrequency(s);
            StudentAtList student = new StudentAtList();
            student.setStudent(s);
            if (!Double.isNaN(avg)){
                student.setGrades(String.valueOf(df.format(avg)));
            }
            if (!Double.isNaN(totalFrequency)){
                student.setFrequency(totalFrequency);
            }
            studentAtLists.add(student);
        }
        model.addAttribute("students", studentAtLists);
        model.addAttribute("group", group);


       return "teacher/classAvg";
    }

    @GetMapping("/student/final-grades/{studentId}")
    public String showStudentFinalGrades(Model model, @PathVariable Long studentId){

        User student = userService.findById(studentId);
        List<Grade> finalGrades = gradeService.findFinalByStudent(studentId);
        Class group = classService.findByStudent(student);
        model.addAttribute("student", student);
        model.addAttribute("finalGrades", finalGrades);
        model.addAttribute("group", group);

        return "/teacher/studentFinalGrades";
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

    @GetMapping("/presence/{presenceId}")
    public String showPresence(Model model, @PathVariable Long presenceId){

        Presence presence = presenceService.findById(presenceId);
        model.addAttribute("presence", presence);
        List<User> presentStudents = presenceService.findPresentStudents(presence);
        List<User> absentStudents = presenceService.findAbsentStudents(presence);
        int presentNmb = presentStudents.size();
        int absentNbb = absentStudents.size();
        model.addAttribute("presentStudents", presentStudents);
        model.addAttribute("absentStudents", absentStudents);
        model.addAttribute("presentNmb", presentNmb);
        model.addAttribute("absentNmb", absentNbb);

        return "teacher/presence";
    }

    @GetMapping("/presence/check-presence/{classId}/{subjectId}")
    public String checkPresenceForm(Model model, @PathVariable Long classId, @PathVariable Long subjectId,
                                   @AuthenticationPrincipal CurrentUser currentUser){
        User teacher = currentUser.getUser();
        Presence presence = new Presence();
        Class group = classService.findById(classId);
        Subject subject = subjectService.findById(subjectId);
        List<User> students = userService.findStudentByClasses(group);
        LocalDate date = LocalDate.now();

        model.addAttribute("teacher", teacher);
        model.addAttribute("presence", presence);
        model.addAttribute("group", group);
        model.addAttribute("subject", subject);
        model.addAttribute("students", students);
        model.addAttribute("date", date);
        return "teacher/checkPresence";
    }

    @PostMapping("/presence/check-presence/{classId}/{subjectId}")
    public String checkPresence(Presence presence, @PathVariable Long classId, @PathVariable Long subjectId,
                                HttpServletRequest request){

        String[] presentStudentsArr = request.getParameterValues("present");
        List<User> presentStudents = new ArrayList<>();
        List<User> absentStudents = new ArrayList<>();
        if (presentStudentsArr == null){
            absentStudents = presenceService.allStudentsAbsent(classId);
        } else {
            presentStudents = presenceService.studentsArrayToList(presentStudentsArr);
            absentStudents = presenceService.absentStudentsList(classId, presentStudents);
        }
        presence.setPresentStudents(Set.copyOf(presentStudents));
        presence.setAbsentStudents(Set.copyOf(absentStudents));
        presence.setDate(LocalDate.now());
        presenceService.save(presence);

        return "redirect:/teacher/class/" + classId + "/" + subjectId;
    }

    @GetMapping("/edit-presence/{presenceId}")
    public String editPresenceForm(Model model, @PathVariable Long presenceId){

        Presence presence = presenceService.findById(presenceId);
        List<User> presentStudents = presenceService.findPresentStudents(presence);
        List<User> absentStudents = presenceService.findAbsentStudents(presence);
        String dateStr = presence.getDate().toString();
        model.addAttribute("presence", presence);
        model.addAttribute("presentStudents", presentStudents);
        model.addAttribute("absentStudents",absentStudents);
        model.addAttribute("dateStr", dateStr);

        return "teacher/editPresence";
    }

    @PostMapping("/edit-presence/{presenceId}")
    public String editPresence(Presence presence, @PathVariable Long presenceId,
                               HttpServletRequest request){

        Class group = presence.getGroup();
        String[] presentStudentsArr = request.getParameterValues("present");
        List<User> presentStudents = presenceService.studentsArrayToList(presentStudentsArr);
        List<User> absentStudents = presenceService.absentStudentsList(group.getId(), presentStudents);
        presence.setPresentStudents(Set.copyOf(presentStudents));
        presence.setAbsentStudents(Set.copyOf(absentStudents));
        String dateStr = request.getParameter("dateStr");
        LocalDate date = LocalDate.parse(dateStr);
        presence.setDate(date);
        presenceService.update(presence);

        return "redirect:/teacher/presence/" + presenceId;
    }
}