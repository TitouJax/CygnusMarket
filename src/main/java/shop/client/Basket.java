package shop.client;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketItem> basketItems;

    public Basket()
    {
        this.basketItems = new ArrayList<>();
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public double getPrice() {
        double price = 0;
        for(int i = 0; i < basketItems.size(); i++) {
            price += basketItems.get(i).getprice();
        }
        return price;
    }

    public boolean addItem(Product product, int quantity) {
        System.out.print("WWWWWWWW");
        for(int i = 0; i < basketItems.size(); i++) {
            if (product.getId() == basketItems.get(i).getProduct().getId())
            {
                basketItems.get(i).setQuantity(basketItems.get(i).getQuantity() + 1);
                return true;
            }
        }
        basketItems.add(new BasketItem(product, quantity));
        return true;
    }
}
