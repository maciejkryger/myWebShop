package pl.javarun.mywebshop.model;


import org.springframework.lang.Nullable;
import javax.persistence.*;



/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 10.06.2020 22:27
 * *
 * @className: TypeCounter
 * *
 * *
 ******************************************************/
@Entity
public class Counter {

    @Id
    private int id;

    @Nullable
    private int type;

    @Nullable
    private int visit;


    public Counter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public int getType() {
        return type;
    }

    public void setType(@Nullable int type) {
        this.type = type;
    }
    @Nullable
    public int getVisit() {
        return visit;
    }


    public void setVisit(@Nullable int visit) {
        this.visit = visit;
    }
}
