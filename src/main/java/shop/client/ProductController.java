package shop.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    List<Product> productList = new ArrayList<Product>();
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
