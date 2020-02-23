package pl.javarun.mywebshop.model;

import javax.persistence.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 22.02.2020 20:36
 * *
 * @className: Rule
 * *
 * *
 ******************************************************/
@Entity
public class Rule {

    @Id
    @GeneratedValue(generator = "ruleSeq")
    @SequenceGenerator(name = "ruleSeq", sequenceName = "rule_seq", allocationSize = 1)
    private int id;
    private String name;
    private String namePl;
    @Column(length = 5000)
    private String description;
    @Column(length = 5000)
    private String descriptionPl;

    public Rule() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionPl() {
        return descriptionPl;
    }

    public void setDescriptionPl(String descriptionPl) {
        this.descriptionPl = descriptionPl;
    }
}
