package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.javarun.mywebshop.model.ColorPerMaterial;
import pl.javarun.mywebshop.model.Material;
import pl.javarun.mywebshop.model.MaterialColor;
import pl.javarun.mywebshop.model.Product;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 22.02.2020 19:24
 * *
 * @className: MaterialColorRepository
 * *
 * *
 ******************************************************/
public interface MaterialColorRepository extends JpaRepository<MaterialColor, Integer> {


    @Query(value = "select distinct mc.*  from material_color mc" +
            " join color_per_material cpm on cpm.material_color_id=mc.id" +
            " join material m on m.id=cpm.material_id " +
            " where UPPER(m.id) = UPPER(:id)", nativeQuery = true)
    List<MaterialColor> findMaterialColorsByMaterialId(int id);




}
