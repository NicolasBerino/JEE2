package springapp.web;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import springapp.business.ProductManager;
import springapp.model.Product;
@Controller()
@RequestMapping("/product")
public class ProductController {
@Autowired
ProductManager manager;
protected final Log logger = LogFactory.getLog(getClass());
@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView listProducts() {
logger.info("List of products");
Collection<Product> products = manager.findAll();
return new ModelAndView("productsList", "products", products);
}
@ModelAttribute("products")
Collection<Product> products() {
logger.info("Making list of products");
return manager.findAll();
}

@RequestMapping(value = "/list", method = RequestMethod.GET)
public String listProducts2() {
logger.info("List of products");
return "productsList";
}

@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String editProduct(@ModelAttribute Product p) {
return "productForm";
}

@ModelAttribute("productTypes")
public Map<String, String> productTypes() {
Map<String, String> types = new LinkedHashMap<>();
types.put("type1", "Type 1");
types.put("type2", "Type 2");
types.put("type3", "Type 3");
types.put("type4", "Type 4");
types.put("type5", "Type 5");
return types;
}

@RequestMapping(value = "/edit", method = RequestMethod.POST)
public String saveProduct(@ModelAttribute Product p, BindingResult result) {
if (result.hasErrors()) {
return "productForm";
}
manager.save(p);
return "productsList";
}

}