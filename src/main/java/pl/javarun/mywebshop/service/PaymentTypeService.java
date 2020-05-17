package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.PaymentTypeNotExistException;
import pl.javarun.mywebshop.model.PaymentType;
import pl.javarun.mywebshop.repository.PaymentTypeRepository;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.05.2020 15:12
 * *
 * @className: PaymentTypeService
 * *
 * *
 ******************************************************/
@Service
public class PaymentTypeService {

    private PaymentTypeRepository paymentTypeRepository;

    public PaymentTypeService(PaymentTypeRepository paymentTypeRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
    }

    public PaymentType getById(int id) {
        return paymentTypeRepository.findById(id).orElseThrow(()->new PaymentTypeNotExistException("Payment Type id-"+ id+" not exist"));
    }

    public PaymentType getByName(String name) {
        return paymentTypeRepository.findByName(name).orElseThrow(()->new PaymentTypeNotExistException("Payment Type name-"+ name+" not exist"));
    }
}
