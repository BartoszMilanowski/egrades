package pl.coderslab.egrades.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.repository.ClassRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClassService {

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public void save(Class group){
        classRepository.save(group);
    }

    public void update(Class group){
        classRepository.save(group);
    }

    public void deleteById(Long id){
        classRepository.deleteById(id);
    }

    public Optional<Class> findById(Long id){
        return classRepository.findById(id);
    }

    public List<Class> findAll(){
        return classRepository.findAll();
    }
}
