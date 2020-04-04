package pl.javarun.mywebshop.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 22.02.2020 18:03
 * *
 * @className: ColorPerMaterial
 * *
 * *
 ******************************************************/
@Entity
public class ColorPerMaterial {

    @Id
    @GeneratedValue(generator = "colorPerMaterialSeq")
    @SequenceGenerator(name = "colorPerMaterialSeq", sequenceName = "color_per_material_seq", allocationSize = 1)
    private int id;
    @ManyToOne(targetEntity = MaterialColor.class)
    private MaterialColor materialColor;
    @ManyToOne(targetEntity = Material.class)
    private Material material;
    private boolean active;
    private String availability;


    public ColorPerMaterial() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MaterialColor getMaterialColor() {
        return materialColor;
    }

    public void setMaterialColor(MaterialColor materialColor) {
        this.materialColor = materialColor;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return  materialColor + " dotępność:" + availability;
    }
}
