package aleksey.ivlev.wh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import aleksey.ivlev.wh.managers.ProductManager;
import aleksey.ivlev.wh.model.Product;


public class ProductController {

	@Autowired
	private ProductManager productService;
	
	public void setProductService(ProductManager productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String listProducts(ModelMap map)
    {
        map.addAttribute("product", new Product());
        map.addAttribute("productsList", productService.getProducts());
 
        return "productsList";
    }

}
