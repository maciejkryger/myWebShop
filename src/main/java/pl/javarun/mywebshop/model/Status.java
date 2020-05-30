package pl.javarun.mywebshop.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 30.05.2020 20:52
 * *
 * @className: Status
 * *
 * *
 ******************************************************/
@Entity
public class Status {

    @Id
    private int id;
    private String name;
    private String namePl;

    public Status() {
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
