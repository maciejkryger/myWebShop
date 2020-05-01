package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.ConfigData;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 01.05.2020 22:10
 * *
 * @className: ConfigurationRepository
 * *
 * *
 ******************************************************/
public interface ConfigDataRepository extends JpaRepository<ConfigData, Integer> {
    ConfigData findByName(String name);
}
