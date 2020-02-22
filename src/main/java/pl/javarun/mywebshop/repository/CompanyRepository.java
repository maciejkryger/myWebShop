package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.Company;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 22.02.2020 14:39
 * *
 * @className: CompanyRepository
 * *
 * *
 ******************************************************/
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
