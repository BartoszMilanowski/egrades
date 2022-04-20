package pl.coderslab.egrades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.egrades.entity.Class;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

    @Query("select c from Class c where c = ?1")
    Class findByOpt(Optional<Class> group);

    }
