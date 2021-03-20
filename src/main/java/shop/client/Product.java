package shop.client;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    private int id;
    private String name;
    private String description;
    private String category;
    private float price;
    private float rating;

    public Product() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.category = "";
        this.price = 0;
        this.rating = 0;
    }
    public Product(int id, String name, String category, float price, float rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.rating = rating;
    }
    public int getId() {
        return id;
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
