package pl.coderslab.egrades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.egrades.entity.Grade;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("select g from Grade g where g.subject.id = ?1 and g.student.id = ?2 and g.isFinal = false")
    List<Grade> findBySubjectAndStudent(Long subjectId, Long studentId);

    @Query("select g from Grade g where g = ?1")
    Grade findByOpt(Optional<Grade> gradeOptional);

    @Query("select g from Grade g where g.subject.id = ?1 and g.student.id= ?2 and g.isFinal = true")
    Grade findFinalBySubjectAndStudent(Long subjectId, Long studentId);

    @Query("select g from Grade g where g.student.id = ?1 and g.isFinal = true ")
    List<Grade> findFinalByStudent(Long studentId);

}
