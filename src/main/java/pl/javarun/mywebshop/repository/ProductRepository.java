package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 19.02.2020 21:28
 * *
 * @className: ProductRepository
 * *
 * *
 ******************************************************/
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Override
    Optional<Product> findById(Integer integer);

    Set<Product> findAllByMaterialColor(String materialColor);

    List<Product> findAllByType_Name(String name);

    List<Product> findAllByType_NameAndActiveIsTrue(String name);

    List<Product> findAllByType_NamePlContainsIgnoreCase(String typeName);

    List<Product> findByNamePlContainsIgnoreCase(String searchWhat);

    List<Product> findByMakingTechnique_NamePlContainsIgnoreCase(String searchWhat);

    List<Product> findByMaterial_NamePlContainsIgnoreCase(String searchWhat);

    List<Product> findByMaterialColor_NamePlContainsIgnoreCase(String searchWhat);

    List<Product> findByFasteningType_NamePlContainsIgnoreCase(String searchWhat);

    List<Product> findByFasteningColor_NamePlContainsIgnoreCase(String searchWhat);

    List<Product> findByLength(Double searchWhat);

    List<Product> findByWidth(Double searchWhat);

    List<Product> findByPrice(int searchWhat);

    List<Product> findByDescriptionPlContainsIgnoreCase(String searchWhat);
}
