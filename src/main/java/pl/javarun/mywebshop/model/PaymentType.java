package pl.javarun.mywebshop.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.05.2020 14:15
 * *
 * @className: PaymentType
 * *
 * *
 ******************************************************/
@Entity
public class PaymentType {

    @Id
    private int id;

    private String name;
    private String namePl;


    public PaymentType() {
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
}
