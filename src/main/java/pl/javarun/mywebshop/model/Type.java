package pl.javarun.mywebshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 17.02.2020 02:33
 * *
 * @className: Type
 * *
 * *
 ******************************************************/
@Entity
public class Type {

    @Id
    @GeneratedValue(generator = "typeSeq")
    @SequenceGenerator(name = "typeSeq",sequenceName = "type_seq",allocationSize = 1)
    private int id;
    private String name;
    private String namePl;

    public Type() {
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
