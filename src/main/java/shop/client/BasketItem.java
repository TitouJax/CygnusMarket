package shop.client;

public class BasketItem {
    private Product product;
    private int quantity;

    public BasketItem(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getprice()
    {
        return product.getPrice() * quantity;
    }
}
