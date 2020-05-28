package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.repository.WebOrderRepository;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.05.2020 12:51
 * *
 * @className: OrderService
 * *
 * *
 ******************************************************/
@Service
public class WebOrderService {

    private WebOrderRepository webOrderRepository;

    public WebOrderService(WebOrderRepository webOrderRepository) {
        this.webOrderRepository = webOrderRepository;
    }

    public void save(WebOrder webOrder) {
        webOrderRepository.save(webOrder);
    }

    public void delete(WebOrder webOrder) {
        webOrderRepository.delete(webOrder);
    }

    public List<WebOrder> getAllOrders(){
        return webOrderRepository.findAll();
    }


    public WebOrder getOrderByUserIdAndConfirmedFalse(int userId) {
        return webOrderRepository.findByUser_IdAndConfirmedFalse(userId).orElseThrow(()->new OrderNotExistException("Zamówienie w bazie dla twojego użytkownika jeszcze nie istnieje!"));
    }

    public WebOrder getOrderById(int orderId) {
        return webOrderRepository.findById(orderId).orElseThrow(()->new OrderNotExistException("Zamówienie w bazie nie istnieje!"));
    }

    public List<WebOrder> getAllConfirmedFalse() {
        return webOrderRepository.findAllByConfirmedIsFalse();
    }

    public List<WebOrder> getAllAcceptedTruePaidFalsePrepayment() {
        return webOrderRepository.findAllByAcceptedTrueAndPaidFalseAndDeliveryOption_PaymentType_IdIs(1);
    }

    public List<WebOrder> getAllConfirmedTrueNotAccepted() {
        return webOrderRepository.findAllByConfirmedIsTrueAndAcceptedFalse();
    }

    public List<WebOrder> getAllReady() {
    return webOrderRepository.getAllByCompletedFalseAndPaidTrueOrPaymentMethod_PaymentType_IdAndCompletedFalse(2);
    }

    public List<WebOrder> getAllSentOrReadyToSelfPickUpOrders() {
        return webOrderRepository.findAllByCompletedTrueAndDeliveryDateIsNull();
    }

    public List<WebOrder> getAllCompletedAndDeliveryDateNotNull() {
        return webOrderRepository.findAllByCompletedTrueAndDeliveryDateNotNull();
    }
}
