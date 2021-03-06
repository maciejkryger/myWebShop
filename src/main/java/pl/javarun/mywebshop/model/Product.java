package pl.javarun.mywebshop.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;

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
//    @GeneratedValue(generator = "productSeq")
//    @SequenceGenerator(name = "productSeq", sequenceName = "product_seq", allocationSize = 1)
    private int id;

    private String name;
    private String namePl;
    @ManyToOne(targetEntity = Type.class)
    private Type type;
    @ManyToOne(targetEntity = Material.class)
    private Material material;
    @Nullable
    @ManyToOne(targetEntity = MaterialColor.class)
    private MaterialColor materialColor;
    @Nullable
    private double length;
    @Nullable
    private double width;
    @ManyToOne(targetEntity = FasteningType.class)
    private FasteningType fasteningType;  //zapięcie
    @Nullable
    @ManyToOne(targetEntity = FasteningColor.class)
    private FasteningColor fasteningColor;
    @ManyToOne(targetEntity = MakingTechnique.class)
    private MakingTechnique makingTechnique;
    private int price;
    @Column(length = 1000)
    private String description;
    @Column(length = 1000)
    private String descriptionPl;
    private boolean active;
    @ManyToOne(targetEntity = Product.class)
    private Product mainProduct;
    @Nullable
    @DateTimeFormat
    private Timestamp creationDate;
    @Nullable
    @DateTimeFormat
    private Timestamp lastUpdateDate;
    @ManyToOne(targetEntity = User.class)
    private User lastUpdateUser;
    @Nullable
    private int discount;


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

    public String getNamePl() {
        return namePl;
    }

    public void setNamePl(String namePl) {
        this.namePl = namePl;
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

    public void setMaterialColor(MaterialColor materialColor) {
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

    public String getDescriptionPl() {
        return descriptionPl;
    }

    public void setDescriptionPl(String descriptionPl) {
        this.descriptionPl = descriptionPl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Product getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(Product mainProduct) {
        this.mainProduct = mainProduct;
    }

    @Nullable
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(@Nullable Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Nullable
    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(@Nullable Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public User getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(User lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    @Nullable
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(@Nullable int discount) {
        this.discount = discount;
    }
}
