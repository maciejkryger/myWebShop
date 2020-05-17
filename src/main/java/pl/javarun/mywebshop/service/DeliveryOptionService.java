package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.DeliveryOptionNotExistException;
import pl.javarun.mywebshop.model.DeliveryOption;
import pl.javarun.mywebshop.repository.DeliveryOptionRepository;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 15.05.2020 23:24
 * *
 * @className: DeliveryOptionService
 * *
 * *
 ******************************************************/
@Service
public class DeliveryOptionService {
    
    private DeliveryOptionRepository deliveryOptionRepository;

    public DeliveryOptionService(DeliveryOptionRepository deliveryOptionRepository) {
        this.deliveryOptionRepository = deliveryOptionRepository;
    }


    public List<DeliveryOption> getAllActiveDeliveryOptions() {
        return deliveryOptionRepository.findAll();
    }

    public DeliveryOption getById(int deliveryOptionId) {
        return deliveryOptionRepository.findById(deliveryOptionId).orElseThrow(()->new DeliveryOptionNotExistException("Wybrana opcja dostawy nie istnieje"));
    }
}
