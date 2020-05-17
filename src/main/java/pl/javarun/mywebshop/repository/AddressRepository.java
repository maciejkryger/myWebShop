package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.Address;
import pl.javarun.mywebshop.model.User;

import java.util.Optional;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 16.05.2020 22:26
 * *
 * @className: AddressRepository
 * *
 * *
 ******************************************************/
public interface AddressRepository extends JpaRepository<Address,Integer> {
    Optional<Address> findByUser(User user);
}
