package pl.javarun.mywebshop.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 01.05.2020 22:06
 * *
 * @className: ConfigData
 * *
 * *
 ******************************************************/
@Entity
public class ConfigData {

    @Id
    private int id;
    private String name;
    private String value;
    private String description;

    public ConfigData() {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
