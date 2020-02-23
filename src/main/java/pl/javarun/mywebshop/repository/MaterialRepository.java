package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.Material;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 23.02.2020 18:39
 * *
 * @className: MaterialRepository
 * *
 * *
 ******************************************************/
public interface MaterialRepository extends JpaRepository<Material,Integer> {
}
