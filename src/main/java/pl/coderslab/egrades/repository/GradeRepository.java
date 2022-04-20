package pl.coderslab.egrades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.egrades.entity.Grade;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("select g from Grade g where g.subject.id = ?1 and g.student.id = ?2")
    List<Grade> findBySubjectAndStudent(Long subjectId, Long studentId);

}
