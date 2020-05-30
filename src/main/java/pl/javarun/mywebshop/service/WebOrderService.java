package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.model.Status;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.repository.StatusRepository;
import pl.javarun.mywebshop.repository.WebOrderRepository;
import pl.javarun.mywebshop.util.EmailOrderChangeStatus;

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

    public List<WebOrder> getAllOrders() {
        return webOrderRepository.findAll();
    }


    public WebOrder getOrderByUserIdAndConfirmedFalse(int userId) {
        return webOrderRepository.findByUser_IdAndConfirmedFalse(userId).orElseThrow(() -> new OrderNotExistException("Zamówienie w bazie dla twojego użytkownika jeszcze nie istnieje!"));
    }

    public WebOrder getOrderById(int orderId) {
        return webOrderRepository.findById(orderId).orElseThrow(() -> new OrderNotExistException("Zamówienie w bazie nie istnieje!"));
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

    public List<WebOrder> getAllActualOrdersByUser(User user) {
        return webOrderRepository.findAllByUserAndDeliveryDateIsNull(user);
    }

    public List<WebOrder> getAllHistoryOrdersByUser(User user) {
        return webOrderRepository.findByUserAndDeliveryDateIsNotNull(user);
    }

    public void updateStatus(WebOrder order, StatusService statusService, EmailOrderChangeStatus emailOrderChangeStatus) {

        if (order.isAccepted() == false) {
            order.setStatus(statusService.getById(1));
            webOrderRepository.save(order);
            emailOrderChangeStatus.send(order);
        }
        if (order.isAccepted() == true && order.isCompleted() == false && order.isPaid() == false && order.getDeliveryOption().getPaymentType().getId() == 1) {
            order.setStatus(statusService.getById(2));
            webOrderRepository.save(order);
            emailOrderChangeStatus.send(order);
        }
        if (order.isAccepted() == true && order.isCompleted() == false && (order.isPaid() == true || order.getDeliveryOption().getPaymentType().getId() == 2)) {
            order.setStatus(statusService.getById(3));
            webOrderRepository.save(order);
            emailOrderChangeStatus.send(order);
        }
        if (order.getDeliveryDate() == null && order.getShipmentNumber() == null && order.getShipmentDate() == null && order.isCompleted() == true) {
            order.setStatus(statusService.getById(4));
            webOrderRepository.save(order);
            emailOrderChangeStatus.send(order);
        }
        if (order.getDeliveryDate() == null && order.getShipmentNumber() != null) {
            order.setStatus(statusService.getById(5));
            webOrderRepository.save(order);
            emailOrderChangeStatus.send(order);
        }
        if (order.getDeliveryDate() != null && order.getShipmentNumber() == null) {
            order.setStatus(statusService.getById(6));
            webOrderRepository.save(order);
            emailOrderChangeStatus.send(order);
        }
        if (order.getDeliveryDate() != null && order.getShipmentNumber() != null) {
            order.setStatus(statusService.getById(7));
            webOrderRepository.save(order);
            emailOrderChangeStatus.send(order);
        }
    }
}
