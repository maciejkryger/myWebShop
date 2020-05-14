package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.OrderNotExistException;
import pl.javarun.mywebshop.model.WebOrder;
import pl.javarun.mywebshop.model.User;
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

    public WebOrder getOrderByUser(User user){
        return webOrderRepository.findByUser(user);
    }

    public WebOrder getOrderByUserIdAndConfirmedFalse(int userId) {
        return webOrderRepository.findByUser_IdAndConfirmedFalse(userId).orElseThrow(()->new OrderNotExistException("Zamówienie w bazie dla twojego użytkownika jeszcze nie istnieje!"));
    }
}
