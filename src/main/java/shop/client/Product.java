package shop.client;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    private int id;
    private String name;
    @Column(length = 2500)
    private String description;
    private String category;
    private double price;

    public Product() {
        this.name = "";
        this.description = "";
        this.category = "";
        this.price = 0;
    }
    public Product(int id, String name, String category, double price, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public int getId() {
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


}
