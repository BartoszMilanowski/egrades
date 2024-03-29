package pl.coderslab.egrades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.Presence;
import pl.coderslab.egrades.entity.Subject;
import pl.coderslab.egrades.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {

    @Query("select p from Presence p where p = ?1")
    Presence findByOpt(Optional<Presence> presenceOpt);

    @Query("select p from Presence p where ?1 member of p.absentStudents or ?1 member of p.presentStudents")
    List<Presence> findByStudent(User student);

    @Query("select p from Presence p where ?1 member of p.presentStudents and p.subject = ?2")
    List<Presence> findByPresentStudent(User student, Subject subject);

    @Query("select p from Presence p where ?1 member of p.absentStudents and p.subject = ?2")
    List<Presence> findByAbsentStudent(User student, Subject subject);

    List<Presence> findByTeacher(User teacher);

    @Query("select p from Presence p where p.subject = ?1 and p.group = ?2")
    List<Presence> findBySubjectAndClass(Subject subject, Class group);

    @Query("select p.presentStudents from Presence p where p = ?1")
    List<User> findPresentStudents(Presence presence);

    @Query("select p.absentStudents from Presence p where p = ?1")
    List<User> findAbsentStudents(Presence presence);


}
