package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.RoleNotExistException;
import pl.javarun.mywebshop.model.Role;
import pl.javarun.mywebshop.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 28.02.2020 20:15
 * *
 * @className: RoleService
 * *
 * *
 ******************************************************/
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElseThrow(()->new RoleNotExistException("role "+id));
    }

    public void save(Role role) {
        roleRepository.save(role);
    }
}
