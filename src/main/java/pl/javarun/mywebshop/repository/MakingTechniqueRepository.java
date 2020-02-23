package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.MakingTechnique;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 23.02.2020 19:35
 * *
 * @className: MakingTechniqueRepository
 * *
 * *
 ******************************************************/
public interface MakingTechniqueRepository extends JpaRepository<MakingTechnique,Integer> {
}
