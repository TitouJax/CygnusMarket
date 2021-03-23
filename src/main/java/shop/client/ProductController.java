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
@RequestMapping("/product/")
public class ProductController {
    private final ProductRepository productRepository;
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

        productRepository.save(paintingNeptune);
        productRepository.save(paintingSaturn);
        productRepository.save(paintingEarth);

        productRepository.save(fragmentMars);
        productRepository.save(fragmentMeteor);
        productRepository.save(fragmentMoon);

        productRepository.save(earthFlight);
        productRepository.save(moonFlight);
        productRepository.save(marsFlight);
    }

    @GetMapping("{category}/{name}")
    public String showProduct(@PathVariable("name") String name, @PathVariable("category") String category, Model model) {
        Product product = productRepository.findByNameIgnoreCaseAndCategoryIgnoreCase(name, category);
        model.addAttribute("product", product);
        return "product";
    }
    @GetMapping("/")
    public String l()
    {
        return "landing";
    }
    @GetMapping("/product/saturn")
    public String s()
    {
        return "product";
    }
    @GetMapping("/search")
    public String u()
    {
        return "search";
    }
    @GetMapping("/checkout")
    public String v()
    {
        return "checkout";
    }
}
