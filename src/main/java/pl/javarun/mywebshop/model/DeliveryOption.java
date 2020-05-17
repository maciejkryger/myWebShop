package pl.javarun.mywebshop.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 15.05.2020 23:17
 * *
 * @className: DeliveryOption
 * *
 * *
 ******************************************************/
@Entity
public class DeliveryOption {

    @Id
    private int id;
    private String name;
    private String namePl;
    @ManyToOne(targetEntity = PaymentType.class)
    private PaymentType paymentType;
    private int price;
    private boolean active;
    private boolean checked;

    public DeliveryOption() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamePl() {
        return namePl;
    }

    public void setNamePl(String namePl) {
        this.namePl = namePl;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
