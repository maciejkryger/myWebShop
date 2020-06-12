package pl.javarun.mywebshop.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.06.2020 22:27
 * *
 * @className: VisitProductCounter
 * *
 * *
 ******************************************************/
@Entity
public class ProductCounter {

    @Id
    private int id;

    @ManyToOne(targetEntity = Product.class)
    private Product product;

    private int visit;

    private int wishList;

    private int basket;

    private int buy;

    public ProductCounter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public int getWishList() {
        return wishList;
    }

    public void setWishList(int wishList) {
        this.wishList = wishList;
    }

    public int getBasket() {
        return basket;
    }

    public void setBasket(int basket) {
        this.basket = basket;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }
}
