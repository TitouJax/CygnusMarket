package shop.client;
import shop.client.Product;

import java.util.List;

public class ProductCategory {
    private List<Product> product;
    private String category;

    ProductCategory(List<Product> product, String category)
    {
        this.product = product;
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public List<Product> getProduct() {
        return product;
    }
}
