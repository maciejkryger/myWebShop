package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.javarun.mywebshop.model.ColorPerMaterial;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 23.02.2020 10:26
 * *
 * @className: ColorPerMaterial
 * *
 * *
 ******************************************************/
public interface ColorPerMaterialRepository extends JpaRepository<ColorPerMaterial, Integer> {

    @Query(value = "select distinct cpm.* from material_color mc" +
            " join color_per_material cpm on cpm.material_color_id=mc.id" +
            " join material m on m.id=cpm.material_id " +
            " where UPPER(m.id) = UPPER(:id) and cpm.active=true", nativeQuery = true)
    List<ColorPerMaterial> findColorsPerMaterialsByIdAndActive(int id);

    List<ColorPerMaterial> findByMaterialNamePlContainsIgnoreCase(String name);

    List<ColorPerMaterial> findByMaterialColorNamePlContainsIgnoreCase(String name);
}
