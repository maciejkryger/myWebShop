package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.model.User;

import java.util.Optional;


/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.05.2020 12:
 * *
 * @className: OrderRepository
 * *
 * *
 ******************************************************/
public interface WebOrderRepository extends JpaRepository<WebOrder, Integer> {

    @Override
    Optional<WebOrder> findById(Integer id);

    Optional<WebOrder> findByUser_IdAndConfirmedFalse(int usersId);

    WebOrder findByUser(User user);
}
