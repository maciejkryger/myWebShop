package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.PaymentMethod;


/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.05.2020 12:59
 * *
 * @className: PaymentMethodRepository
 * *
 * *
 ******************************************************/
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {


}
