package pl.coderslab.egrades.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.Role;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void update(User user){
        userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User  findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> findByRoles(Role role){
        return userRepository.findByRoles(role);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public List<User> findStudentByClasses(Class group){
        return userRepository.findStudentByClasses(group);
    }

    public User findByOpt(Optional<User> userOptional){
        return userRepository.findByOpt(userOptional);
    }
}
