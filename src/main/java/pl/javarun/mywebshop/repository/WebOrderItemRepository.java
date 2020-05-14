package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.WebOrderItem;

import java.util.List;
import java.util.Optional;


/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.05.2020 12:47
 * *
 * @className: OrderItemRepository
 * *
 * *
 ******************************************************/
public interface WebOrderItemRepository extends JpaRepository<WebOrderItem, Integer> {

    @Override
    Optional<WebOrderItem> findById(Integer id);


    Optional<WebOrderItem> findByWebOrder_IdAndProduct_Id(int orderId, int productId);

    List<WebOrderItem> findByWebOrder_Id(int orderId);
}
