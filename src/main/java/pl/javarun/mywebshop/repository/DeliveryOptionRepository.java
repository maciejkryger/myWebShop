package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.DeliveryOption;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 15.05.2020 23:24
 * *
 * @className: DeliveryOptionRepository
 * *
 * *
 ******************************************************/
public interface DeliveryOptionRepository extends JpaRepository<DeliveryOption, Integer> {


}
