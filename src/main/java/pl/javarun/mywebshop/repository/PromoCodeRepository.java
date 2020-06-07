package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.PromoCode;
import pl.javarun.mywebshop.model.Role;

import java.util.List;
import java.util.Optional;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 06.06.2020 21:44
 * *
 * @className: PromoCodeRepository
 * *
 * *
 ******************************************************/
public interface PromoCodeRepository extends JpaRepository<PromoCode, Integer> {

    Optional<PromoCode> findByCode(String discountCode);
}
