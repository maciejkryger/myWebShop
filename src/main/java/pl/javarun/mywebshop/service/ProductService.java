package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.ProductNotExistException;
import pl.javarun.mywebshop.model.Product;
import pl.javarun.mywebshop.repository.ProductRepository;

import java.util.List;
import java.util.Set;

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

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

    public List<Product> getProductsByTypeName(String typeName){
        return productRepository.findAllByType_Name(typeName);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(()->new ProductNotExistException("product "+id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
