package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.FasteningType;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 23.02.2020 19:11
 * *
 * @className: FasteningTypeRepository
 * *
 * *
 ******************************************************/
public interface FasteningTypeRepository extends JpaRepository<FasteningType, Integer> {
}
