package pl.coderslab.egrades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.Role;
import pl.coderslab.egrades.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByRoles(Role role);

    @Query("select u from User u where 1 member of u.roles and ?1 member of u.classes")
    List<User> findStudentByClasses(Class group);

    @Query("select u from User u where u = ?1")
    User findByOpt(Optional<User> userOptional);

    @Query("select g.student from Grade g where g.id =?1")
    User findStudentByGradeId(Long gradeId);
}