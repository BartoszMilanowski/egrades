package pl.coderslab.egrades.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.Role;
import pl.coderslab.egrades.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void save(Role role){
        roleRepository.save(role);
    }

    public void update(Role role){
        roleRepository.save(role);
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Optional<Role> findByName(String name){
        return roleRepository.findByName(name);
    }

    public void deleteById(int id){
        roleRepository.deleteById(id);
    }
}
