package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.ProductNotExistException;
import pl.javarun.mywebshop.model.Product;
import pl.javarun.mywebshop.search.SearchProductModelOption;
import pl.javarun.mywebshop.repository.ProductRepository;

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

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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

    public List<Product> searchProducts(String searchWhat, SearchProductModelOption findBy) {
        switch (findBy) {
            case BY_PRODUCT_NAME:
                return productRepository.findByNamePlContainsIgnoreCase(searchWhat);
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
            case BY_FASTENING_COLOR:
                return productRepository.findByFasteningColor_NamePlContainsIgnoreCase(searchWhat);
            case BY_LENGTH:
                    return productRepository.findByLength(Double.valueOf(searchWhat));
            case BY_WIGHT:
                return productRepository.findByWidth(Double.valueOf(searchWhat));
            case BY_PRICE:
                return productRepository.findByPrice(Integer.valueOf(searchWhat));
            case BY_PRODUCT_DESCRIPTION:
                return productRepository.findByDescriptionPlContainsIgnoreCase(searchWhat);
            default:
                return Collections.emptyList();
        }
    }
}
