package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.Role;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 15.02.2020 18:11
 * *
 * @className: RoleRepository
 * *
 * *
 ******************************************************/
public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findByAuthority(String authority);
}
