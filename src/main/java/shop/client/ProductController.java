package shop.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    Logger logger = LoggerFactory.getLogger("logger");
    public final Basket basket = new Basket();
    public final ProductRepository productRepository;
    @Autowired
    public ProductController(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public List<String> getAllCategories()
    {
        List<Product> products = productRepository.findAllByOrderByCategory();
        List<String> categories = new ArrayList<>();
        for (int i = 0; i < products.size(); i++)
        {
            if (!categories.contains(products.get(i).getCategory())) {
                categories.add(products.get(i).getCategory());
            }
        }
        return categories;
    }

    @RequestMapping("/search/")
    public String searchPage(@RequestParam String keyword, Model model) {
        logger.info("Search page loaded");
        List<Product> products = productRepository.findByCategoryIgnoreCaseOrNameIgnoreCase(keyword, keyword);
        model.addAttribute("products", products);
        model.addAttribute("categories", getAllCategories());
        model.addAttribute("number", products.size());
        model.addAttribute("keyword", keyword);
        if (products.size() == 0)
        {
            model.addAttribute("msg","product.");
        }
        else
        {
            model.addAttribute("msg", "products:");
        }
        model.addAttribute("basket", basket);
        return "search";
    }

    @GetMapping("/checkout/")
    public String checkoutPage(@RequestParam(required=false) String id, Model model)
    {
        logger.info("Checkout loaded");
        model.addAttribute("categories", getAllCategories());
        model.addAttribute("basket", basket.getBasketItems());
        model.addAttribute("globalBasket", basket);
        model.addAttribute("message", (basket.getBasketItems().size() == 0) ? "Your basket is empty." : "Your basket:");
        logger.info("Baskets is containing " + basket.getBasketItems().size() + " items :");
        for (int i = 0; i < basket.getBasketItems().size(); i++)
        {
            Product p = basket.getBasketItems().get(i).getProduct();
            logger.info("Product: " + p.getName() + " " + p.getCategory() + ", quantity: "
                    + basket.getBasketItems().get(i).getQuantity());
        }
        return "checkout";
    }

    @GetMapping("/product/{category}/{name}")
    public String productPage(@PathVariable("name") String name, @PathVariable("category") String category, Model model) {
        logger.info("Product page " + name + " " + category);
        Product product = productRepository.findByNameIgnoreCaseAndCategoryIgnoreCase(name, category);
        model.addAttribute("product", product);
        model.addAttribute("categories", getAllCategories());
        model.addAttribute("basket", basket);
        model.addAttribute("buy", "basket.addItem(product, 1)");
        List<Product> allByCat = productRepository.findByCategoryIgnoreCase(category);
        for (int i = 0; i < allByCat.size(); i++)
        {
            if (allByCat.get(i).getName().equals(name))
            {
                allByCat.remove(i);
            }
        }
        model.addAttribute("msg", (allByCat.size() == 0) ? "" : "You may also be interested by:");
        model.addAttribute("allExc", allByCat);
        return "product";
    }

    @GetMapping("/product/{category}/{name}/buy")
    public String buy(@PathVariable("name") String name, @PathVariable("category") String category, Model model)
    {
        logger.info("Bought " + name + " " + category);
        Product product = productRepository.findByNameIgnoreCaseAndCategoryIgnoreCase(name, category);
        model.addAttribute("product", product);
        model.addAttribute("categories", getAllCategories());
        model.addAttribute("basket", basket);
        model.addAttribute("buy", "basket.addItem(product, 1)");
        List<Product> allByCat = productRepository.findByCategoryIgnoreCase(category);
        for (int i = 0; i < allByCat.size(); i++)
        {
            if (allByCat.get(i).getName().equals(name))
            {
                allByCat.remove(i);
            }
        }
        model.addAttribute("msg", (allByCat.size() == 0) ? "" : "You may also be interested in:");
        model.addAttribute("allExc", allByCat);
        return "buy";
    }

    @GetMapping("/")
    public String landingPage(Model model)
    {
        logger.info("Landing page loaded");
        List<ProductCategory> products = new ArrayList<>();
        List<String> categories = getAllCategories();
        int index = 0;
        for (int i = 0; i < categories.size(); i++) {
            products.add(new ProductCategory(productRepository.findByCategoryIgnoreCase(categories.get(i)), categories.get(i)));
        }
        model.addAttribute("ProductsCategories", products);
        model.addAttribute("basket", basket);
        return "landing";
    }

}
