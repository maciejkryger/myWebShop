package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.model.ProductCounter;
import pl.javarun.mywebshop.repository.ProductCounterRepository;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.06.2020 22:24
 * *
 * @className: ProductCounterService
 * *
 * *
 ******************************************************/
@Service
public class ProductCounterService {

    private ProductCounterRepository productCounterRepository;


    public ProductCounter getByProductId(int id) {
        return productCounterRepository.findByProduct_Id(id);
    }

    public void save(ProductCounter productCounter) {
        productCounterRepository.save(productCounter);
    }
}
