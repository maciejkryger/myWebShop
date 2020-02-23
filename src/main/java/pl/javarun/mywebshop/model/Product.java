package pl.javarun.mywebshop.model;

import javax.persistence.*;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 15.02.2020 17:40
 * *
 * @className: Product
 * *
 * *
 ******************************************************/
@Entity
public class Product {

    @Id
    @GeneratedValue(generator = "productSeq")
    @SequenceGenerator(name = "productSeq", sequenceName = "product_seq", allocationSize = 1)
    private int id;

    private String name;
    @ManyToOne(targetEntity = Type.class)
    private Type type;
    @ManyToOne(targetEntity = Material.class)
    private Material material;
    @ManyToOne(targetEntity = MaterialColor.class)
    private MaterialColor materialColor;
    private double length;
    private double width;
    @ManyToOne(targetEntity = FasteningType.class)
    private FasteningType fasteningType;  //zapięcie
    @ManyToOne(targetEntity = FasteningColor.class)
    private FasteningColor fasteningColor;
    @ManyToOne(targetEntity = MakingTechnique.class)
    private MakingTechnique makingTechnique;
    private int price;
    @Column(length = 1000)
    private String description;

    public Product() {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public MaterialColor getMaterialColor() {
        return materialColor;
    }

    public void setMaterialColor(MaterialColor color) {
        this.materialColor = materialColor;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public FasteningType getFasteningType() {
        return fasteningType;
    }

    public void setFasteningType(FasteningType fasteningType) {
        this.fasteningType = fasteningType;
    }

    public FasteningColor getFasteningColor() {
        return fasteningColor;
    }

    public void setFasteningColor(FasteningColor fasteningColor) {
        this.fasteningColor = fasteningColor;
    }

    public MakingTechnique getMakingTechnique() {
        return makingTechnique;
    }

    public void setMakingTechnique(MakingTechnique makingTechnique) {
        this.makingTechnique = makingTechnique;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
