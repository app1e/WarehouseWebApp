package aleksey.ivlev.wh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import aleksey.ivlev.wh.managers.DicStoresManager;
import aleksey.ivlev.wh.managers.ProductManager;

@Controller
public class ReportController {

	@Autowired
	private ProductManager productManager;

	@Autowired
	private DicStoresManager dicStoresManager;

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public void setDicStoresManager(DicStoresManager dicStoresManager) {
		this.dicStoresManager = dicStoresManager;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listProducts(ModelMap map) {
		map.addAttribute("productsList", productManager.getProducts());
		map.addAttribute("dicStores", dicStoresManager.getAllDicStores());
		map.addAttribute("list", productManager.getReports());
		return "report";
	}
}
