package pl.coderslab.egrades.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.User;
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

    public Class findById(Long id){
        Optional<Class> classOptional = classRepository.findById(id);
        return classRepository.findByOpt(classOptional);
    }
    public List<Class> findAll(){
        return classRepository.findAll();
    }

    public Class findByStudent(User student){
        return classRepository.findByStudent(student);
    }

    public List<Class> findOtherClasses(Class group){
        List<Class> classes = classRepository.findAll();
        classes.remove(group);
        return classes;
    }

    public List<User> findStudents(Long groupId){
        Class group = findById(groupId);
        return classRepository.findStudents(group);
    }
}