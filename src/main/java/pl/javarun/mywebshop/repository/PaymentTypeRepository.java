package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.PaymentType;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.05.2020 15:09
 * *
 * @className: PaymentTypeRepository
 * *
 * *
 ******************************************************/
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {
}
