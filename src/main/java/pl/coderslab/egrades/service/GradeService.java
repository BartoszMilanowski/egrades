package pl.coderslab.egrades.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.Grade;
import pl.coderslab.egrades.entity.Subject;
import pl.coderslab.egrades.entity.User;
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

    public Grade findFinalBySubjectAndStudent(Long subjectId, Long studentId){
        return gradeRepository.findFinalBySubjectAndStudent(subjectId, studentId);
    }

    public List<Grade> findFinalByStudent(Long studentId){
        return gradeRepository.findFinalByStudent(studentId);
    }

    public double averageFinalGrade(List<Grade> finalGrades) {
        int sum = 0;
        double avg = 0;
        if (!finalGrades.isEmpty()) {
            for (int i = 0; i < finalGrades.size(); i++) {
                sum += finalGrades.get(i).getGradeValue();
            }
            avg = sum / finalGrades.size();
        }
        return avg;
    }

    public String gradesToList(Subject subject, User student){

        List<Grade> grades = findBySubjectAndStudent(subject.getId(), student.getId());
        String gradesToList = new String();

        for (Grade g : grades){
            gradesToList = new StringBuilder(gradesToList).append(g.getGradeValue()) + " ";
        }
        return gradesToList;
    }

    public double avgGrade(Subject subject, User student){
        List<Grade> grades = findBySubjectAndStudent(subject.getId(), student.getId());
        double sum = 0;

        for (Grade g : grades){
            sum += g.getGradeValue();
        }

        return sum/grades.size();
    }

}
