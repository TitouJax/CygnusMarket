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
        Product paintingNeptune = new Product("Neptune", "Neptune is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius of about nine times that of Earth.[20][21] It only has one-eighth the average density of Earth; however, with its larger volume, Saturn is over 95 times more massive.[", "Painting", 35f, 4.0f);
        Product paintingSaturn = new Product("Saturn", "Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius of about nine times that of Earth.[20][21] It only has one-eighth the average density of Earth; however, with its larger volume, Saturn is over 95 times more massive.[", "Painting", 42f, 4.0f);
        Product paintingEarth= new Product("Earth", "Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius of about nine times that of Earth.[20][21] It only has one-eighth the average density of Earth; however, with its larger volume, Saturn is over 95 times more massive.[", "Painting", 36f, 4.0f);

        // Fragments
        Product fragmentMeteor = new Product("Meteor", "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon.", "Fragment", 31f, 63f);
        Product fragmentMoon = new Product("Moon", "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon.", "Fragment", 8f, 33f);
        Product fragmentMars = new Product("Mars", "A meteorite is a solid piece of debris from an object, such as a comet, asteroid, or meteoroid, that originates in outer space and survives its passage through the atmosphere to reach the surface of a planet or moon.", "Fragment", 97f, 24f);

        productRepository.save(paintingNeptune);
        productRepository.save(paintingSaturn);
        productRepository.save(paintingEarth);
        productRepository.save(fragmentMars);
        productRepository.save(fragmentMeteor);
        productRepository.save(fragmentMoon);
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
