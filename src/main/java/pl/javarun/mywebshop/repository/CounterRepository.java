package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.Counter;

import java.util.Optional;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.06.2020 22:36
 * *
 * @className: TypeCounterRepository
 * *
 * *
 ******************************************************/
public interface CounterRepository extends JpaRepository<Counter,Integer> {

    Optional<Counter> findByType(int type);
}
