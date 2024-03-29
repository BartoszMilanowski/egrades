package pl.coderslab.egrades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.egrades.entity.Subject;
import pl.coderslab.egrades.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Subject findBySubjectName(String name);

    @Query("select s from Subject s where ?1 member of s.teachers")
    List<Subject> findByTeachers(User teacher);

    @Query("select s from Subject s where s = ?1")
    Subject findByOpt(Optional<Subject> subjectOptional);

    @Query("select g.subject from Grade g where g.id = ?1")
    Subject findSubjectByGradeId(Long gradeId);

    @Query("select s.teachers from Subject s where s = ?1")
    List<User> findTeachers(Subject subject);
}
