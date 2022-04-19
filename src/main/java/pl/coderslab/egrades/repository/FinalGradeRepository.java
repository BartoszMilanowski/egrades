package pl.coderslab.egrades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.egrades.entity.FinalGrade;

import java.util.List;

@Repository
public interface FinalGradeRepository extends JpaRepository<FinalGrade, Long> {

    @Query("select f from FinalGrade f where f.subject.id = ?1 and f.student.id = ?2")
    FinalGrade findBySubjectAndStudent(Long subjectId, Long studentId);

    @Query("select f from FinalGrade f where f.student.id = ?1")
    List<FinalGrade> findByStudent(Long studentId);
}
