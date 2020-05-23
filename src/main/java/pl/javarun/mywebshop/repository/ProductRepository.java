package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.javarun.mywebshop.model.Product;
import pl.javarun.mywebshop.model.Type;

import java.util.List;
import java.util.Optional;


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
    Optional<Product> findById(Integer id);

    List<Product> findAllByActiveIsTrue();

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

    List<Product> findAllByMaterial_IdAndActiveIsTrue(int materiaId);

    List<Product> findAllByMaterialColor_IdAndActiveIsTrue(Integer materialColorId);

    List<Product> findAllByType_IdAndActiveIsTrue(Integer typeId);

    List<Product> findAllByFasteningType_IdAndActiveIsTrue(Integer fasteningTypeId);

    List<Product> findAllByPriceBetweenAndActiveIsTrue(Integer priceFrom, Integer priceTo);

    List<Product> findAllByMaterial_IdAndMaterialColor_IdAndActiveIsTrue(Integer materialId, Integer materialColorId);

    List<Product> findAllByMaterial_IdAndMaterialColor_IdAndType_IdAndActiveIsTrue(Integer materialId, Integer materialColorId, Integer typeId);

    List<Product> findAllByMaterial_IdAndMaterialColor_IdAndType_IdAndFasteningType_IdAndActiveIsTrue(Integer materialId, Integer materialColorId, Integer typeId, Integer fasteningTypeId);

    List<Product> findAllByMaterial_IdAndMaterialColor_IdAndType_IdAndFasteningType_IdAndPriceBetweenAndActiveIsTrue(Integer materialId, Integer materialColorId, Integer typeId, Integer fasteningTypeId, Integer priceFrom, Integer priceTo);

    List<Product> findAllByPriceGreaterThanEqualAndActiveIsTrue(Integer priceFrom);

    List<Product> findAllByPriceLessThanEqualAndActiveIsTrue(Integer priceTo);

    List<Product> findAllByMaterial_IdAndMaterialColor_IdAndType_IdAndFasteningType_IdAndPriceGreaterThanEqualAndActiveIsTrue(Integer materialId, Integer materialColorId, Integer typeId, Integer fasteningTypeId, Integer priceFrom);

    List<Product> findAllByMaterial_IdAndMaterialColor_IdAndType_IdAndFasteningType_IdAndPriceLessThanEqualAndActiveIsTrue(Integer materialId, Integer materialColorId, Integer typeId, Integer fasteningTypeId, Integer priceTo);

    List<Product> findAllByMaterial_IdAndMaterialColor_IdAndFasteningType_IdAndActiveTrue(Integer materialId, Integer materialColorId, Integer fasteningTypeId);

    List<Product> findAllByMaterial_IdAndMaterialColor_IdAndFasteningType_IdAndPriceBetweenAndActiveTrue(Integer materialId, Integer materialColorId, Integer fasteningTypeId, Integer priceFrom, Integer priceTo);

    List<Product> findAllByMaterial_IdAndMaterialColor_IdAndFasteningType_IdAndPriceLessThanEqualAndActiveTrue(Integer materialId, Integer materialColorId, Integer fasteningTypeId, Integer priceTo);

    List<Product> findAllByMaterial_IdAndMaterialColor_IdAndFasteningType_IdAndPriceGreaterThanEqualAndActiveTrue(Integer materialId, Integer materialColorId, Integer fasteningTypeId, Integer priceFrom);

    List<Product> findAllByType_IdAndFasteningType_IdAndActiveTrue(Integer typeId, Integer fasteningTypeId);

    List<Product> findAllByType_IdAndFasteningType_IdAndPriceGreaterThanEqualAndActiveTrue(Integer typeId, Integer fasteningTypeId, Integer priceFrom);

    List<Product> findAllByType_IdAndFasteningType_IdAndPriceLessThanEqualAndActiveTrue(Integer typeId, Integer fasteningTypeId, Integer priceTo);

    List<Product> findAllByType_IdAndFasteningType_IdAndPriceBetweenAndActiveTrue(Integer typeId, Integer fasteningTypeId, Integer priceFrom, Integer priceTo);

    List<Product> findAllByMaterialColor_IdAndType_IdAndFasteningType_IdAndPriceBetweenAndActiveTrue(Integer materialColorId, Integer typeId, Integer fasteningTypeId, Integer priceFrom, Integer priceTo);

    List<Product> findAllByMaterial_IdAndType_IdAndFasteningType_IdAndPriceBetweenAndActiveTrue(Integer materialId, Integer typeId, Integer fasteningTypeId, Integer priceFrom, Integer priceTo);

    List<Product> findAllByMaterial_IdAndFasteningType_IdAndPriceBetweenAndActiveTrue(Integer materialId, Integer fasteningTypeId, Integer priceFrom, Integer priceTo);

    List<Product> findAllByMaterial_IdAndPriceBetweenAndActiveTrue(Integer materialId, Integer priceFrom, Integer priceTo);

    List<Product> findAllByMaterial_IdAndPriceGreaterThanEqualAndActiveTrue(Integer materialId, Integer priceFrom);

    List<Product> findAllByMaterial_IdAndPriceLessThanEqualAndActiveTrue(Integer materialId, Integer priceTo);

    List<Product> findAllByMainProduct(Object o);

    @Query(value = "select distinct p.* from product p where (p.id = :id or p.main_product_id= :id ) and p.active=true",
            nativeQuery = true)
    List<Product> findAllByFamilyAndActive(int id);

    List<Product> findAllByMainProduct_NamePlContainsIgnoreCase(String name);

    @Query(value = "select distinct p.* and wl.* from product p join wish_list wl on p.id=wl.product_id where (wl.user_id = :id and p.active=true and p.type.name=:type)",
            nativeQuery = true)
    List<Product> findAllActiveProductsWithWishListTagByProductTypeNameAndUserId(String type, int id);


}
