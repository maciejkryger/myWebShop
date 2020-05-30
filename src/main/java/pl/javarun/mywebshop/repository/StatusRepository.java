package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.Material;
import pl.javarun.mywebshop.model.Status;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 30.05.2020 21:06
 * *
 * @className: StatusRepository
 * *
 * *
 ******************************************************/
public interface StatusRepository extends JpaRepository<Status,Integer> {
}
