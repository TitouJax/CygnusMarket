package shop.client;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(length = 2500)
    private String description;
    private String category;
    private double price;
    private float rating;

    public Product() {
        this.name = "";
        this.description = "";
        this.category = "";
        this.price = 0;
        this.rating = 0;
    }
    public Product(String name, String category, double price, float rating, String description) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() { return name; }

    public String getCategory() { return category; }

    public String getDescription() { return description; }

    public double getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }


}
