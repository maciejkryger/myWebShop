package pl.javarun.mywebshop.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 06.06.2020 21:38
 * *
 * @className: PromoCode
 * *
 * *
 ******************************************************/
@Entity
public class PromoCode {

    @Id
    private int id;

    private String code;
    private int discount;
    private boolean active;


    public PromoCode() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
