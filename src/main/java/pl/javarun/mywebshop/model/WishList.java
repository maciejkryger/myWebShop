package pl.javarun.mywebshop.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.05.2020 14:51
 * *
 * @className: WishList
 * *
 * * Ulubione produkty użytkownika
 ******************************************************/
@Entity
public class WishList {

    @Id
    private int id;

    @ManyToOne(targetEntity = User.class)
    private User user;
    @ManyToOne(targetEntity = Product.class)
    private Product product;

    public WishList() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
