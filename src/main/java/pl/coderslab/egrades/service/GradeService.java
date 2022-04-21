package pl.coderslab.egrades.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.Grade;
import pl.coderslab.egrades.repository.GradeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public void save(Grade grade){
        gradeRepository.save(grade);
    }

    public void update(Grade grade){
        gradeRepository.save(grade);
    }

    public void deleteById(Long id){
        gradeRepository.deleteById(id);
    }

    public List<Grade> findAll(){
        return gradeRepository.findAll();
    }

    public List<Grade> findBySubjectAndStudent(Long subjectId, Long studentId){
        return gradeRepository.findBySubjectAndStudent(subjectId, studentId);
    }

    public Grade findById(Long gradeId){
        Optional<Grade> gradeOptional = gradeRepository.findById(gradeId);
        return gradeRepository.findByOpt(gradeOptional);
    }
}
