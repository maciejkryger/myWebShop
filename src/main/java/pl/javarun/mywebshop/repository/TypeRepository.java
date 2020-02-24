package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.Type;

import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 19.02.2020 22:01
 * *
 * @className: TypeRepository
 * *
 * *
 ******************************************************/
public interface TypeRepository extends JpaRepository<Type, Integer> {

    @Override
    Optional<Type> findById(Integer integer);
    List<Type> findAllByName(String name);
    Optional<Type> findByName(String name);
    Optional<Type> findByNamePl(String namePl);
}
