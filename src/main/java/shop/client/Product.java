package shop.client;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    private int id;
    private String name;
    private float price;
    private float rating;

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }
}
