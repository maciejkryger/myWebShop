package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.PaymentMethodNotExistException;
import pl.javarun.mywebshop.model.PaymentMethod;
import pl.javarun.mywebshop.repository.PaymentMethodRepository;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.05.2020 12:59
 * *
 * @className: PaymentMethodService
 * *
 * *
 ******************************************************/
@Service
public class PaymentMethodService {

    private PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public void save(PaymentMethod paymentMethod) {
        paymentMethodRepository.save(paymentMethod);
    }

    public void delete(PaymentMethod paymentMethod) {
        paymentMethodRepository.delete(paymentMethod);
    }

    public List<PaymentMethod> getByPaymentTypeId(int id) {
        return paymentMethodRepository.findByPaymentType_Id(id);
    }

    public PaymentMethod getById(int paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId).orElseThrow(()->new PaymentMethodNotExistException("wybrana metoda płatności nie istnieje"));
    }
}
