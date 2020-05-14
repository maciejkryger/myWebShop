package pl.javarun.mywebshop.service;

import org.springframework.stereotype.Service;
import pl.javarun.mywebshop.model.WishList;
import pl.javarun.mywebshop.repository.WishListRepository;

import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.05.2020 15:12
 * *
 * @className: WishListService
 * *
 * *
 ******************************************************/
@Service
public class WishListService {

    private WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }


    public List<WishList> getAllWishListByUserId(int id) {
        return wishListRepository.findAllByUser_Id(id);
    }


    public WishList getWishListByUserIdAndProductId(int userId, Integer productId) {
        return wishListRepository.findByUser_IdAndProduct_Id(userId, productId);
    }

    public void save(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public void delete(WishList wishList) {
        wishListRepository.delete(wishList);
    }
}
