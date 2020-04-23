package pl.javarun.mywebshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 16.02.2020 00:49
 * *
 * @className: Technique
 * *
 * *
 ******************************************************/
@Entity
public class MakingTechnique {

    @Id
//    @GeneratedValue(generator = "makingTechniqueSeq")
//    @SequenceGenerator(name = "makingTechniqueSeq", sequenceName ="making_technique_seq" , allocationSize = 1)
    private int id;
    private String name;
    private String namePl;

    public MakingTechnique() {
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
