package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.User;

import java.util.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 15.02.2020 22:59
 * *
 * @className: UserRepository
 * *
 * *
 ******************************************************/
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameIgnoreCase(String username);

    List<User> findAllByUsernameContainsIgnoreCase(String username);

    List<User> findAllByFirstNameContainsIgnoreCase(String firstName);

    List<User> findAllByLastNameContainsIgnoreCase(String lastName);

    Optional<User> findByToken(String token);

    Set<User> findAllByActiveTrue();

    Set<User> findAllByActiveFalse();

    Set<User> findAllByCreationDateGreaterThan(Date date);

    Set<User> findByRoleAuthorityContainsIgnoreCase(String authority);

    Optional<User> findById(int id);


    Optional <User> findByEmailContainsIgnoreCase(String email);

    List<User> findAllByEmailContainsIgnoreCase(String email);


    List<User> findAllByRole_AuthorityContainsIgnoreCase(String authority);

    List<User> findAllByActive(Boolean active);
}
