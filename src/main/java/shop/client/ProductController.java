package shop.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {
    public final ProductRepository productRepository;
    private boolean generated = false;
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
        products.add(new Product("Earth", "Flight", 250000.95, 5, "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon."));
        products.add(new Product("Moon", "Flight", 1250000.95, 5, "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon."));
        products.add(new Product("Moon", "Flight", 1250000.95, 5, "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon."));
        products.add(new Product("Mars", "Flight", 80000000.95,5, "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon."));

        products.add(new Product("Mars", "Planet", 250000.95, 5, "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon."));

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
/*        List<Product> flights = productRepository.findByCategoryIgnoreCase("Flight");
        List<Product> fragments = productRepository.findByCategoryIgnoreCase("Fragment");
        List<Product> paintings = productRepository.findByCategoryIgnoreCase("Painting");
        model.addAttribute("flights", flights);
        model.addAttribute("fragments", fragments);
        model.addAttribute("paintings", paintings);*/
        ProductController p = new ProductController(productRepository);
        List<String> categories = p.getAllCategories();
        List<ProductCategory> products = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < categories.size(); i++) {
            products.add(new ProductCategory(productRepository.findByCategoryIgnoreCase(categories.get(i)), categories.get(i)));
        }
        model.addAttribute("ProductsCategories", products);
        return "landing";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "search";
    }
    @GetMapping("/product/{category}/{name}")
    public String productPage(@PathVariable("name") String name, @PathVariable("category") String category, Model model) {
        Product product = productRepository.findByNameIgnoreCaseAndCategoryIgnoreCase(name, category);
        model.addAttribute("product", product);
        return "product";
    }
}
