package shop.client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    private String name;
    @Column(length = 2500)
    private String description;
    private String category;
    private float price;
    private float rating;

    public Product() {
        this.name = "";
        this.description = "";
        this.category = "";
        this.price = 0;
        this.rating = 0;
    }
    public Product(String name, String description, String category, float price, float rating) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.rating = rating;
    }


    public String getName() { return name; }

    public String getCategory() { return category; }

    public String getDescription() { return description; }

    public float getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }


}
