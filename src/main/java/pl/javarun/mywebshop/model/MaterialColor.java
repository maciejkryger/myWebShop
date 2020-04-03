package pl.javarun.mywebshop.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 21.02.2020 21:39
 * *
 * @className: Color
 * *
 * *
 ******************************************************/
@Entity
public class MaterialColor {

    @Id
//    @GeneratedValue(generator = "materialColorSeq")
//    @SequenceGenerator(name = "materialColorSeq", sequenceName = "material_color_seq", allocationSize = 1)
    private int id;
    private String name;
    private String namePl;


    public MaterialColor() {
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
