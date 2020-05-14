package pl.javarun.mywebshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javarun.mywebshop.model.WishList;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.05.2020 15:09
 * *
 * @className: WishListRepository
 * *
 * *
 ******************************************************/
public interface WishListRepository extends JpaRepository<WishList, Integer> {


    List<WishList> findAllByUser_Id(int id);

    WishList findByUser_IdAndProduct_Id(int userId, Integer productId);
}
