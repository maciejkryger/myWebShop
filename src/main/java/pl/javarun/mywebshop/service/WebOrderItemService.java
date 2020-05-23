package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.exception.OrderItemNotExistException;
import pl.javarun.mywebshop.model.WebOrderItem;
import pl.javarun.mywebshop.repository.WebOrderItemRepository;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.05.2020 12:51
 * *
 * @className: OrderItemService
 * *
 * *
 ******************************************************/
@Service
public class WebOrderItemService {

    private WebOrderItemRepository webOrderItemRepository;

    public WebOrderItemService(WebOrderItemRepository webOrderItemRepository) {
        this.webOrderItemRepository = webOrderItemRepository;
    }

    public void save(WebOrderItem webOrderItem) {
        webOrderItemRepository.save(webOrderItem);
    }

    public void delete(WebOrderItem webOrderItem) {
        webOrderItemRepository.delete(webOrderItem);
    }

    public List<WebOrderItem> getAllOrders() {
        return webOrderItemRepository.findAll();
    }


    public WebOrderItem getOrderItemOrderIdAndProductId(int orderId, int productId) {
        return webOrderItemRepository.findByWebOrder_IdAndProduct_Id(orderId, productId).
                orElseThrow(() ->
                        new OrderItemNotExistException("OrderItem for orderId: " + orderId +
                                " and productId " + productId + " not Extist"));
    }

    public List<WebOrderItem> getOrderItemByOrderId(int orderId) {
        return webOrderItemRepository.findByWebOrder_Id(orderId);
    }
}
