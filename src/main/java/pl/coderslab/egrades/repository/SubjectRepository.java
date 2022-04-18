package pl.coderslab.egrades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.egrades.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Subject findBySubjectName(String name);
}
