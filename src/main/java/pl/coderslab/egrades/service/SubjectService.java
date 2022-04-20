package pl.coderslab.egrades.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.Subject;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.repository.SubjectRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
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

}
