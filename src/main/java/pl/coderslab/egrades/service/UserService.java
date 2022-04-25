package pl.coderslab.egrades.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.Role;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class UserService{

    private final UserRepository userRepository;

    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
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

    public User findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        return userRepository.findByOpt(optionalUser);
    }

    public List<User> findStudentByClasses(Class group){
        return userRepository.findStudentByClasses(group);
    }

    public User findStudentByGradeId(Long gradeId){
        return userRepository.findStudentByGradeId(gradeId);
    }

    public List<User> findTeachersAndAdmins(){
        List<User> teachers = findByRoles(roleService.findById(2));
        List<User> admins = findByRoles(roleService.findById(3));
        return Stream.concat(teachers.stream(), admins.stream()).collect(Collectors.toList());
    }
}
