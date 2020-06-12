package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.ProductCounter;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.06.2020 22:36
 * *
 * @className: ProductCounterRepository
 * *
 * *
 ******************************************************/
public interface ProductCounterRepository extends JpaRepository<ProductCounter,Integer> {

    ProductCounter findByProduct_Id(int id);
}
