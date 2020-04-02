package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.User;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 15.02.2020 22:59
 * *
 * @className: UserRepository
 * *
 * *
 ******************************************************/
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsernameIgnoreCase(String username);

    Set<User> findByFirstNameContainsIgnoreCase(String firstName);

    Set<User> findByLastNameContains(String lastName);

    Set<User> findByToken(String token);

    Set<User> findAllByActiveTrue();

    Set<User> findAllByActiveFalse();

    Set<User> findAllByCreationDateGreaterThan(Date date);

    Set<User> findByRoleAuthorityContainsIgnoreCase(String authority);

}
