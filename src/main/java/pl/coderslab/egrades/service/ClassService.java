package pl.coderslab.egrades.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.repository.ClassRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Transactional
    public void save(Class group){
        classRepository.save(group);
    }

    @Transactional
    public void update(Class group){
        classRepository.save(group);
    }

    @Transactional
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
       List<Class> allClasses = findAll();
       List<Class> classes = new ArrayList<>();
       for (Class c : allClasses){
           if (c.getId() != group.getId()){
               classes.add(c);
           }
       }
        return classes;
    }

    public List<User> findStudents(Long groupId){
        Class group = findById(groupId);
        return classRepository.findStudents(group);
    }
}