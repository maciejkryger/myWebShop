package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.Rule;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 22.02.2020 20:36
 * *
 * @className: Rule
 * *
 * *
 ******************************************************/
public interface RuleRepository extends JpaRepository<Rule,Integer> {
    Rule findByName(String name);
}
