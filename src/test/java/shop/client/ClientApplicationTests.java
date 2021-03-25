package shop.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientApplicationTests {

	@Test
	void contextLoads() {

		// Tests on product
		Product p1 = new Product(0, "p1", "catp1", 5, "descp1");

		assert (p1.getId() == 0);
		assert (p1.getName().equals("p1"));
		assert (p1.getCategory().equals("catp1"));
		assert (p1.getPrice() == 5);
		assert (p1.getDescription().equals("descp1"));

		Product p2 = new Product(1, "p2", "catp2", 6, "descp2");
		assert (p2.getId() == 1);
		assert (p2.getName().equals("p2"));
		assert (p2.getCategory().equals("catp2"));
		assert (p2.getPrice() == 6);
		assert (p2.getDescription().equals("descp2"));

		// Tests on basket
		Basket basket = new Basket();

		basket.addItem(p1, 1);
		assert (basket.getBasketItems().get(0).getProduct() == p1);

		basket.addItem(p1, 1);
		assert (basket.getBasketItems().get(0).getQuantity() == 2);

		basket.addItem(p2, 18);
		assert (basket.getBasketItems().get(1).getQuantity() == 18);
	}

}
