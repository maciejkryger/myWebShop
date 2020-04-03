package pl.javarun.mywebshop.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 16.02.2020 00:46
 * *
 * @className: Material
 * *
 * *
 ******************************************************/
@Entity
public class Material {

    @Id
//    @GeneratedValue(generator = "materialSeq")
//    @SequenceGenerator(name = "materialSeq", sequenceName = "material_seq", allocationSize = 1)
    private int id;

    private String name;
    private String namePl;

    //TODO sprawdzić jak zrobić listę kolorów w oparciu o relacje
//    @OneToMany(targetEntity = MaterialColor.class)
//    private List<MaterialColor> materialColorList;

    public Material() {
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
