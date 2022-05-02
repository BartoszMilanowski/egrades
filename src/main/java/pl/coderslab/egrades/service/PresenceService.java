package pl.coderslab.egrades.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.Presence;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.repository.PresenceRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PresenceService {

    private final PresenceRepository presenceRepository;

    public PresenceService(PresenceRepository presenceRepository) {
        this.presenceRepository = presenceRepository;
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
}
