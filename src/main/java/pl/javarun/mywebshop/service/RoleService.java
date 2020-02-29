package pl.javarun.mywebshop.service;

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
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }
}
