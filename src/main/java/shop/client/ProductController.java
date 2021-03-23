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
    @Autowired
    public ProductController(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
        // Paintings
        Product paintingNeptune = new Product(0, "Neptune", "Neptune is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius of about nine times that of Earth.[20][21] It only has one-eighth the average density of Earth; however, with its larger volume, Saturn is over 95 times more massive.[", "Painting", 35f, 4.0f);
        Product paintingSaturn = new Product(1, "Saturn", "Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius of about nine times that of Earth.[20][21] It only has one-eighth the average density of Earth; however, with its larger volume, Saturn is over 95 times more massive.[", "Painting", 42f, 4.0f);
        Product paintingEarth= new Product(2, "Earth", "Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius of about nine times that of Earth.[20][21] It only has one-eighth the average density of Earth; however, with its larger volume, Saturn is over 95 times more massive.[", "Painting", 36f, 4.0f);

        // Fragments
        Product fragmentMeteor = new Product(3, "Meteor", "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon.", "Fragment", 31f, 63f);
        Product fragmentMoon = new Product(4, "Moon", "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon.", "Fragment", 8f, 33f);
        Product fragmentMars = new Product(5, "Mars", "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon.", "Fragment", 97f, 24f);

        // Flights
        Product earthFlight = new Product(6, "Earth", "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon.", "Flight", 350000f, 5f);
        Product moonFlight = new Product(7, "Moon", "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon.", "Flight", 2350000f, 5f);
        Product marsFlight = new Product(8, "Mars", "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon.", "Flight", 55000000f, 5f);

        Product marsPlanet = new Product(9, "Mars", "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon.", "Planet", 55000000f, 5f);

        productRepository.save(paintingNeptune);
        productRepository.save(paintingSaturn);
        productRepository.save(paintingEarth);

        productRepository.save(fragmentMars);
        productRepository.save(fragmentMeteor);
        productRepository.save(fragmentMoon);

        productRepository.save(earthFlight);
        productRepository.save(moonFlight);
        productRepository.save(marsFlight);

        productRepository.save(marsPlanet);
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
