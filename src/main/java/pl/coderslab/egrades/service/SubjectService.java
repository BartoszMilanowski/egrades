package pl.coderslab.egrades.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.Subject;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.repository.SubjectRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final UserService userService;

    public SubjectService(SubjectRepository subjectRepository, UserService userService) {
        this.subjectRepository = subjectRepository;
        this.userService = userService;
    }

    public void save(Subject subject){
        subjectRepository.save(subject);
    }

    public void update(Subject subject){
        subjectRepository.save(subject);
    }

    public void deleteById(Long id){
        subjectRepository.deleteById(id);
    }

    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

    public Subject findByName(String subjectName){
        return subjectRepository.findBySubjectName(subjectName);
    }

    public Subject findById(Long id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        return subjectRepository.findByOpt(optionalSubject);
    }

    public List<Subject> findByTeachers(User teacher){
        return subjectRepository.findByTeachers(teacher);
    }

    public Subject findSubjectByGradeId(Long gradeId){
        return subjectRepository.findSubjectByGradeId(gradeId);
    }

    public void addTeacherToSubject(Subject subject, User teacher){
        Set<User> teachers = subject.getTeachers();
        teachers.add(teacher);
        subject.setTeachers(teachers);
        update(subject);
    }

    public List<User> findTeachers(Subject subject){
        return subjectRepository.findTeachers(subject);
    }

    public void removeTeacherFromSubject(Long subjectId, Long teacherId){
        Subject subject = findById(subjectId);
        List<User> teachers = findTeachers(subject);
        List<User> newTeachers = new ArrayList<>();
        for (User t : teachers){
            if (t.getId() != teacherId){
                newTeachers.add(t);
            }
        }
        subject.setTeachers(Set.copyOf(newTeachers));
    }

    public List<User> showOtherTeachers(Long subjectId){
        Subject subject = findById(subjectId);
        List<User> currentTeachers = findTeachers(subject);
        List<User> otherTeachers = userService.findTeachersAndAdmins();
        otherTeachers.removeAll(currentTeachers);
        return otherTeachers;
    }

    public void addTeachersToSubject(Long subjectId, String[] newTeachersArray){
        Subject subject = findById(subjectId);
        List<User> currentTeachers = findTeachers(subject);
        List<User> newTeachers = new ArrayList<>();

        for (String s : newTeachersArray){
            newTeachers.add(userService.findById(Long.parseLong(s)));
        }
        for (User u : newTeachers){
            currentTeachers.add(u);
        }
    }
}
