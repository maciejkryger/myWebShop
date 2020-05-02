package pl.javarun.mywebshop.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.UserNotExistException;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.repository.UserRepository;
import pl.javarun.mywebshop.search.SearchUserModelOption;

import java.util.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 15.02.2020 23:13
 * *
 * @className: UserService
 * *
 * *
 ******************************************************/
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsernameIgnoreCase(s).orElseThrow(() -> new UsernameNotFoundException("" + s));
    }

    public User saveUser(User user) {
        if (user.getUsername().equals("") || user.getEmail().equals("") || user.getFirstName().equals("") || user.getLastName().equals("")) {
            throw new UserNotExistException("Could not create user without details. Please fill out the required fields");
        }
        return userRepository.save(user);
    }

    public void deleteUser(User user, Date date) {
        user.setDeleted(true);
        user.setDeletingDate(date);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotExistException("id: " + id + " not exist"));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new UserNotExistException("username: " + username + " not exist"));
    }

    public Set<User> getAllUsers() {
        return new HashSet<>(userRepository.findAll());
    }

    public Set<User> getAllActiveUsers() {
        return userRepository.findAllByActiveTrue();
    }

    public Set<User> getAllNotActiveUsers() {
        return userRepository.findAllByActiveFalse();
    }

    public Set<User> getUsersByAuthority(String authority) {
        return userRepository.findByRoleAuthorityContainsIgnoreCase(authority);
    }

    public User getUserByToken(String token) {
        return userRepository.findByToken(token).orElseThrow(() -> new UserNotExistException("user with token: " + token + " not exist"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmailContainsIgnoreCase(email).orElseThrow(() -> new UserNotExistException("user with email: " + email + " not exist"));
    }

    public Collection<User> searchUser(String searchWhat, SearchUserModelOption findBy) {
        switch (findBy) {
            case BY_USERNAME:
                return userRepository.findAllByUsernameContainsIgnoreCase(searchWhat);
            case BY_FIRSTNAME:
                return userRepository.findAllByFirstNameContainsIgnoreCase(searchWhat);
            case BY_LASTNAME:
                return userRepository.findAllByLastNameContainsIgnoreCase(searchWhat);
            case BY_EMAIL:
                return userRepository.findAllByEmailContainsIgnoreCase(searchWhat);
            case BY_ROLE:
                return userRepository.findAllByRole_AuthorityContainsIgnoreCase(searchWhat);
            case BY_ACTIVE:
                return userRepository.findAllByActive(Boolean.valueOf(searchWhat));
            case BY_CREATIONDATE:
                return userRepository.findAllByCreationDateGreaterThan(java.sql.Date.valueOf(searchWhat));
            default:
                return Collections.emptyList();
        }
    }
}
