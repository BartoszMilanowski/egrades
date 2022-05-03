package pl.coderslab.egrades.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.Presence;
import pl.coderslab.egrades.entity.Subject;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.repository.PresenceRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PresenceService {

    private final PresenceRepository presenceRepository;

    private final UserService userService;

    private final ClassService classService;

    public PresenceService(PresenceRepository presenceRepository, UserService userService, ClassService classService) {
        this.presenceRepository = presenceRepository;
        this.userService = userService;
        this.classService = classService;
    }

    public void save(Presence presence){
        presenceRepository.save(presence);
    }

    public Presence findById(Long presenceId){
        Optional<Presence> presenceOptional = presenceRepository.findById(presenceId);
        return presenceRepository.findByOpt(presenceOptional);
    }

    public void deleteById(Long presenceId){
        presenceRepository.deleteById(presenceId);
    }

    public void update(Presence presence){
        presenceRepository.save(presence);
    }

    public List<Presence> findByStudent(User student){
        return presenceRepository.findByStudent(student);
    }

    public List<Presence> findByPresentStudent(User student){
        return presenceRepository.findByPresentStudent(student);
    }

    public List<Presence> findByAbsentStudent(User student){
        return presenceRepository.findByAbsentStudent(student);
    }

    public List<Presence> findByTeacher(User teacher){
        return presenceRepository.findByTeacher(teacher);
    }

    public List<Presence> findBySubjectAndClass(Subject subject, Class group){
        return presenceRepository.findBySubjectAndClass(subject, group);
    }

    public List<User> findPresentStudents(Presence presence){
        return presenceRepository.findPresentStudents(presence);
    }

    public List<User> findAbsentStudents(Presence presence){
        return presenceRepository.findAbsentStudents(presence);
    }

    public List<User> studentsArrayToList(String[] array){
        List<User> list = new ArrayList<>();
        for (String s : array){
            User student = userService.findById(Long.parseLong(s));
            list.add(student);
        }
        return list;
    }

    public List<User> absentStudentsList(Long classId, List<User> presentStudents){
       List<User> holeClassList = userService.findStudentByClasses(classService.findById(classId));
//       List<User> absentStudents = new ArrayList<>();
       for (int i = 0; i < holeClassList.size(); i++){
           for (int j = 0; j < presentStudents.size(); j++){
               if (holeClassList.get(i).getId().equals(presentStudents.get(j).getId())){
                   holeClassList.remove(holeClassList.get(i));
               }
           }
       }
       return holeClassList;
    }
}
