package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.ProductNotExistException;
import pl.javarun.mywebshop.model.Product;
import pl.javarun.mywebshop.model.Type;
import pl.javarun.mywebshop.search.SearchProductModelOption;
import pl.javarun.mywebshop.repository.ProductRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Collections;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 19.02.2020 21:27
 * *
 * @className: ProductService
 * *
 * *
 ******************************************************/
@Service
public class ProductService {

    private ProductRepository productRepository;
    private ConfigDataService configDataService ;

    public ProductService(ProductRepository productRepository, ConfigDataService configDataService) {
        this.productRepository = productRepository;
        this.configDataService=configDataService;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    public List<Product> getActiveProductsByTypeName(String typeName) {
        return productRepository.findAllByType_NameAndActiveIsTrue(typeName);
    }

    public List<Product> getActiveProductsByTypeNamePl(String typeName) {
        return productRepository.findAllByType_NamePlContainsIgnoreCase(typeName);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotExistException("product " + id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllActiveProducts() {
        return productRepository.findAllByActiveIsTrue();
    }

    public List<Product> searchProducts(String searchWhat, SearchProductModelOption findBy) {
        switch (findBy) {
            case BY_PRODUCT_NAME:
                return productRepository.findByNamePlContainsIgnoreCase(searchWhat);
            case BY_MAIN_PRODUCT:
                return productRepository.findAllByMainProduct_NamePlContainsIgnoreCase(searchWhat);
            case BY_TYPE:
                return productRepository.findAllByType_NamePlContainsIgnoreCase(searchWhat);
            case BY_MAKING_TECHNIQUE:
                return productRepository.findByMakingTechnique_NamePlContainsIgnoreCase(searchWhat);
            case BY_MATERIAL:
                return productRepository.findByMaterial_NamePlContainsIgnoreCase(searchWhat);
            case BY_MATERIAL_COLOR:
                return productRepository.findByMaterialColor_NamePlContainsIgnoreCase(searchWhat);
            case BY_FASTENING_TYPE:
                return productRepository.findByFasteningType_NamePlContainsIgnoreCase(searchWhat);
//            case BY_FASTENING_COLOR:
//                return productRepository.findByFasteningColor_NamePlContainsIgnoreCase(searchWhat);
            case BY_LENGTH:
                return productRepository.findByLength(Double.valueOf(searchWhat));
            case BY_WIGHT:
                return productRepository.findByWidth(Double.valueOf(searchWhat));
            case BY_PRICE:
                return productRepository.findByPrice(Integer.parseInt(searchWhat));
            case BY_PRODUCT_DESCRIPTION:
                return productRepository.findByDescriptionPlContainsIgnoreCase(searchWhat);
            default:
                return Collections.emptyList();
        }
    }

    public List<Product> getActiveProductsByMaterialId(Integer materialId) {
        return productRepository.findAllByMaterial_IdAndActiveIsTrue(materialId);
    }

    public List<Product> getActiveProductsByMaterialColorId(Integer materialColorId) {
        return productRepository.findAllByMaterialColor_IdAndActiveIsTrue(materialColorId);
    }

    public List<Product> getActiveProductsByTypeId(Integer typeId) {
        return productRepository.findAllByType_IdAndActiveIsTrue(typeId);
    }

    public List<Product> getActiveProductsByFasteningTypeId(Integer fasteningTypeId) {
        return productRepository.findAllByFasteningType_IdAndActiveIsTrue(fasteningTypeId);
    }

    public List<Product> getActiveProductsByPriceBetween(Integer priceFrom, Integer priceTo) {
        return productRepository.findAllByPriceBetweenAndActiveIsTrue(priceFrom, priceTo);
    }

    public List<Product> getActiveProductsByMaterialIdAndMaterialColorId(Integer materialId, Integer materialColorId) {
        return productRepository.findAllByMaterial_IdAndMaterialColor_IdAndActiveIsTrue(materialId, materialColorId);
    }

    public List<Product> getActiveProductsByMaterialIdAndMaterialColorIdAndTypeId(Integer materialId, Integer materialColorId, Integer typeId) {
        return productRepository.findAllByMaterial_IdAndMaterialColor_IdAndType_IdAndActiveIsTrue(materialId, materialColorId, typeId);
    }

    public List<Product> getActiveProductsByMaterialIdAndMaterialColorIdAndTypeIdAndFasteningTypeId(Integer materialId, Integer materialColorId, Integer typeId, Integer fasteningTypeId) {
        return productRepository.findAllByMaterial_IdAndMaterialColor_IdAndType_IdAndFasteningType_IdAndActiveIsTrue(materialId, materialColorId, typeId, fasteningTypeId);
    }

    public List<Product> getActiveProductsByMaterialIdAndMaterialColorIdAndTypeIdAndFasteningTypeIdAndPriceBetween(Integer materialId, Integer materialColorId, Integer typeId, Integer fasteningTypeId, Integer priceFrom, Integer priceTo) {
        return productRepository.findAllByMaterial_IdAndMaterialColor_IdAndType_IdAndFasteningType_IdAndPriceBetweenAndActiveIsTrue(materialId, materialColorId, typeId, fasteningTypeId, priceFrom, priceTo);
    }

    public List<Product> getActiveProductsByPriceAbove(Integer priceFrom) {
        return productRepository.findAllByPriceGreaterThanEqualAndActiveIsTrue(priceFrom);
    }

    public List<Product> getActiveProductsByPriceUpTo(Integer priceTo) {
        return productRepository.findAllByPriceLessThanEqualAndActiveIsTrue(priceTo);
    }

    public List<Product> getActiveProductsByMaterialIdAndMaterialColorIdAndTypeIdAndFasteningTypeIdAndPriceAbove(Integer materialId, Integer materialColorId, Integer typeId, Integer fasteningTypeId, Integer priceFrom) {
        return productRepository.findAllByMaterial_IdAndMaterialColor_IdAndType_IdAndFasteningType_IdAndPriceGreaterThanEqualAndActiveIsTrue(materialId, materialColorId, typeId, fasteningTypeId, priceFrom);
    }

    public List<Product> getActiveProductsByMaterialIdAndMaterialColorIdAndTypeIdAndFasteningTypeIdAndPriceUpTo(Integer materialId, Integer materialColorId, Integer typeId, Integer fasteningTypeId, Integer priceTo) {
        return productRepository.findAllByMaterial_IdAndMaterialColor_IdAndType_IdAndFasteningType_IdAndPriceLessThanEqualAndActiveIsTrue(materialId, materialColorId, typeId, fasteningTypeId, priceTo);
    }

    public List<Product> getActiveProductsByMaterialIdAndMaterialColorIdAndFasteningTypeId(Integer materialId, Integer materialColorId, Integer fasteningTypeId) {
        return productRepository.findAllByMaterial_IdAndMaterialColor_IdAndFasteningType_IdAndActiveTrue(materialId, materialColorId, fasteningTypeId);
    }

    public List<Product> getActiveProductsByMaterialIdAndMaterialColorIdAndFasteningTypeIdAndPriceBetween(Integer materialId, Integer materialColorId, Integer fasteningTypeId, Integer priceFrom, Integer priceTo) {
        return productRepository.findAllByMaterial_IdAndMaterialColor_IdAndFasteningType_IdAndPriceBetweenAndActiveTrue(materialId, materialColorId, fasteningTypeId, priceFrom,priceTo);
    }

    public List<Product> getActiveProductsByMaterialIdAndMaterialColorIdAndFasteningTypeIdAndPriceUpTo(Integer materialId, Integer materialColorId, Integer fasteningTypeId, Integer priceTo) {
        return productRepository.findAllByMaterial_IdAndMaterialColor_IdAndFasteningType_IdAndPriceLessThanEqualAndActiveTrue(materialId, materialColorId, fasteningTypeId, priceTo);
    }

    public List<Product> getActiveProductsByMaterialIdAndMaterialColorIdAndFasteningTypeIdAndPriceAbove(Integer materialId, Integer materialColorId, Integer fasteningTypeId, Integer priceFrom) {
        return productRepository.findAllByMaterial_IdAndMaterialColor_IdAndFasteningType_IdAndPriceGreaterThanEqualAndActiveTrue(materialId, materialColorId, fasteningTypeId, priceFrom);
    }

    public List<Product> getActiveProductsByTypeIdAndFasteningId(Integer typeId, Integer fasteningTypeId) {
        return productRepository.findAllByType_IdAndFasteningType_IdAndActiveTrue(typeId,fasteningTypeId);
    }

    public List<Product> getActiveProductsByTypeIdAndFasteningIdAndPriceAbove(Integer typeId, Integer fasteningTypeId, Integer priceFrom) {
        return productRepository.findAllByType_IdAndFasteningType_IdAndPriceGreaterThanEqualAndActiveTrue(typeId,fasteningTypeId,priceFrom);
    }

    public List<Product> getActiveProductsByTypeIdAndFasteningIdAndPriceUpTo(Integer typeId, Integer fasteningTypeId, Integer priceTo) {
        return productRepository.findAllByType_IdAndFasteningType_IdAndPriceLessThanEqualAndActiveTrue(typeId,fasteningTypeId,priceTo);
    }

    public List<Product> getActiveProductsByTypeIdAndFasteningIdAndPriceBetween(Integer typeId, Integer fasteningTypeId, Integer priceFrom, Integer priceTo) {
        return productRepository.findAllByType_IdAndFasteningType_IdAndPriceBetweenAndActiveTrue(typeId,fasteningTypeId,priceFrom,priceTo);
    }

    public List<Product> getActiveProductsByMaterialColorIdAndTypeIdAndFasteningIdAndPriceBetween(Integer materialColorId, Integer typeId, Integer fasteningTypeId, Integer priceFrom, Integer priceTo) {
        return productRepository.findAllByMaterialColor_IdAndType_IdAndFasteningType_IdAndPriceBetweenAndActiveTrue(materialColorId,typeId,fasteningTypeId,priceFrom,priceTo);
    }

    public List<Product> getActiveProductsByMaterialIdAndTypeIdAndFasteningIdAndPriceBetween(Integer materialId, Integer typeId, Integer fasteningTypeId, Integer priceFrom, Integer priceTo) {
        return productRepository.findAllByMaterial_IdAndType_IdAndFasteningType_IdAndPriceBetweenAndActiveTrue(materialId,typeId,fasteningTypeId,priceFrom,priceTo);
    }

    public List<Product> getActiveProductsByMaterialIdAndFasteningIdAndPriceBetween(Integer materialId, Integer fasteningTypeId, Integer priceFrom, Integer priceTo) {
        return productRepository.findAllByMaterial_IdAndFasteningType_IdAndPriceBetweenAndActiveTrue(materialId,fasteningTypeId,priceFrom,priceTo);
    }

    public List<Product> getActiveProductsByMaterialIdAndPriceBetween(Integer materialId, Integer priceFrom, Integer priceTo) {
        return productRepository.findAllByMaterial_IdAndPriceBetweenAndActiveTrue(materialId,priceFrom,priceTo);
    }

    public List<Product> getActiveProductsByMaterialIdAndPriceAbove(Integer materialId, Integer priceFrom) {
        return productRepository.findAllByMaterial_IdAndPriceGreaterThanEqualAndActiveTrue(materialId,priceFrom);
    }

    public List<Product> getActiveProductsByMaterialIdAndPriceUpTo(Integer materialId, Integer priceTo) {
        return productRepository.findAllByMaterial_IdAndPriceLessThanEqualAndActiveTrue(materialId,priceTo);
    }

    public List<Product> getAllMainProducts() {
        return productRepository.findAllByMainProduct(null);
    }


    public List<Product> getActiveProductsGroupByMainId(int id){
        return productRepository.findAllByFamilyAndActive(id);
    }

    public List<Product> getAllActiveProductsWithWishListTagByProductTypeNameAndUserId(String type, int id){
        return productRepository.findAllActiveProductsWithWishListTagByProductTypeNameAndUserId(type,id);}

    public List<Product> getActiveProductsByNewPeriod() {
        int newProductPeriod = Integer.parseInt(configDataService.getConfigDataByName("newProductPeriod").getValue());
        Timestamp newProductPeriodDate = Timestamp.valueOf(LocalDateTime.now().minus(Period.ofDays(newProductPeriod)));
        return productRepository.findAllByActiveIsTrueAndCreationDateAfter(newProductPeriodDate);
    }

    public List<Product> getActiveProductsByDiscount() {
        return productRepository.findAllByActiveIsTrueAndDiscountGreaterThan(0);
    }
}
