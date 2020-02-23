package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.FasteningColor;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 23.02.2020 19:12
 * *
 * @className: FasteningColorRepository
 * *
 * *
 ******************************************************/
public interface FasteningColorRepository extends JpaRepository<FasteningColor, Integer> {
}
