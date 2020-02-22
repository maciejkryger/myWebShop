package pl.javarun.mywebshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 22.02.2020 13:51
 * *
 * @className: FasteningColor
 * *
 * *
 ******************************************************/
@Entity
public class FasteningColor {

    @Id
    @GeneratedValue(generator = "fasteningColorSeq")
    @SequenceGenerator(name = "fasteningcClorSeq", sequenceName = "fastening_color_seq", allocationSize = 1)
    private int id;
    private String name;
    private String namePl;

    public FasteningColor() {
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
