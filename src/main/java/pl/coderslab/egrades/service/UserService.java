package pl.coderslab.egrades.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.Class;
import pl.coderslab.egrades.entity.Role;
import pl.coderslab.egrades.entity.User;
import pl.coderslab.egrades.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService{

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    @Transactional
    public void update(User user){
        user.setEnabled(1);
        String password = findById(user.getId()).getPassword();
        user.setPassword(password);
        userRepository.save(user);
    }

    @Transactional
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
        List<User> teachers = findByRoles(roleService.findByName("ROLE_TEACHER"));
        List<User> admins = findByRoles(roleService.findByName("ROLE_ADMIN"));
        return Stream.concat(teachers.stream(), admins.stream()).collect(Collectors.toList());
    }

    public List<User> findAllStudents(){
        return findByRoles(roleService.findByName("ROLE_STUDENT"));
    }

    public List<User> showOtherTeachers(User teacher){
        List<User> teachers = findTeachersAndAdmins();
        List<User> otherTeachers = new ArrayList<>();
        for (User t : teachers){
            if (t.getId() != teacher.getId()){
                otherTeachers.add(t);
            }
        }
        return otherTeachers;
    }
}
