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
    public ModelAndView getList() {
        String viewname = "landing";
        Map<String, Object> model = new HashMap<String, Object>();
        productList.clear();
        productList.add(new Product());
        productList.add(new Product(1,"Test1", "Ship", 1, 2));
        model.put("productList", productList);
        return new ModelAndView(viewname, model);
    }
}
