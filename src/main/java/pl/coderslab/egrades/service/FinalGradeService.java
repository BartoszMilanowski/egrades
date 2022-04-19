package pl.coderslab.egrades.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.FinalGrade;
import pl.coderslab.egrades.repository.FinalGradeRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FinalGradeService {

    private final FinalGradeRepository finalGradeRepository;

    public FinalGradeService(FinalGradeRepository finalGradeRepository) {
        this.finalGradeRepository = finalGradeRepository;
    }

    public void save(FinalGrade finalGrade){
        finalGradeRepository.save(finalGrade);
    }

    public void update(FinalGrade finalGrade){
        finalGradeRepository.save(finalGrade);
    }

    public void delete(Long finalGradeId){
        finalGradeRepository.deleteById(finalGradeId);
    }

    public FinalGrade findBySubjectAndStudent(Long subjectId, Long studentId){
        return finalGradeRepository.findBySubjectAndStudent(subjectId, studentId);
    }

    public List<FinalGrade> findByStudent(Long studentId){
        return finalGradeRepository.findByStudent(studentId);
    }

}
