package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.Product;
import pl.javarun.mywebshop.model.Type;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 19.02.2020 21:28
 * *
 * @className: ProductRepository
 * *
 * *
 ******************************************************/
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Override
    Optional<Product> findById(Integer integer);
    Set<Product> findAllByColor(String color);
    List<Product> findAllByType_Name(String name);
}
