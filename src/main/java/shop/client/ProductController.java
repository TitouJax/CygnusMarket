package shop.client;

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
@RequestMapping("/")
public class ProductController {
    public final ProductRepository productRepository;
    Basket basket = new Basket();
    @Autowired
    public ProductController(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
        List<Product> products = new ArrayList<>();
            // Paintings
        products.add(new Product("Neptune", "Painting", 45.99, 5, "Neptune is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius of about nine times that of Earth.[20][21] It only has one-eighth the average density of Earth; however, with its larger volume, Saturn is over 95 times more massive.["));
        products.add(new Product("Saturn", "Painting", 45.99, 5, "Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius of about nine times that of Earth.[20][21] It only has one-eighth the average density of Earth; however, with its larger volume, Saturn is over 95 times more massive.["));
        products.add(new Product("Earth", "Painting", 45.99, 5, "Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius of about nine times that of Earth.[20][21] It only has one-eighth the average density of Earth; however, with its larger volume, Saturn is over 95 times more massive.["));

            // Fragments
        products.add(new Product("Meteor", "Fragment", 45.99, 5, "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon."));
        products.add(new Product("Moon", "Fragment", 33.99, 5, "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon."));
        products.add(new Product("Mars", "Fragment", 89.99, 5, "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon."));

            // Flights
        products.add(new Product("Earth", "Flight", 250000, 5, "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon."));
        products.add(new Product("Moon", "Flight", 1250000, 5, "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon."));
        products.add(new Product("Saturn", "Flight", 10850000, 5, "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon."));
        products.add(new Product("Mars", "Flight", 80000000,5, "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon."));

        products.add(new Product("Mars", "Planet", 250000, 5, "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon."));

        for(int i = 0; i < products.size(); i++)
        {
            products.get(i).setId(i);
            productRepository.save(products.get(i));
        }
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
    @GetMapping("/")
    public String landingPage(Model model)
    {
        ProductController p = new ProductController(productRepository);
        List<String> categories = p.getAllCategories();
        List<ProductCategory> products = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < categories.size(); i++) {
            products.add(new ProductCategory(productRepository.findByCategoryIgnoreCase(categories.get(i)), categories.get(i)));
        }
        model.addAttribute("ProductsCategories", products);
        model.addAttribute("basket", basket);
        return "landing";
    }

    @RequestMapping("/search/")
    public String searchPage(@RequestParam String keyword, Model model) {
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
        return "search";
    }
    @GetMapping("/checkout/")
    public String checkoutPage(@RequestParam(required=false) String id, Model model)
    {
        model.addAttribute("categories", getAllCategories());
        model.addAttribute("basket", basket.getBasketItems());
        return "checkout";
    }

    @GetMapping("/product/{category}/{name}")
    public String productPage(@PathVariable("name") String name, @PathVariable("category") String category, Model model) {
        Product product = productRepository.findByNameIgnoreCaseAndCategoryIgnoreCase(name, category);
        model.addAttribute("product", product);
        model.addAttribute("categories", getAllCategories());
        model.addAttribute("basket", basket);
        return "product";
    }
}
