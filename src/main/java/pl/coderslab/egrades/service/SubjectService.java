package pl.coderslab.egrades.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.Subject;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.repository.SubjectRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final UserService userService;

    public SubjectService(SubjectRepository subjectRepository, UserService userService) {
        this.subjectRepository = subjectRepository;
        this.userService = userService;
    }

    @Transactional
    public void save(Subject subject){
        subjectRepository.save(subject);
    }

    @Transactional
    public void update(Subject subject){
        subjectRepository.save(subject);
    }

    @Transactional
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
      List<User> teachers = findTeachers(subject);
      teachers.add(teacher);
      subject.setTeachers(Set.copyOf(teachers));
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

    public List<Subject> showOtherSubjects(User teacher){
        List<Subject> currentSubjects = findByTeachers(teacher);
        List<Subject> otherSubjects = findAll();
        otherSubjects.removeAll(currentSubjects);
        return otherSubjects;
    }
}
