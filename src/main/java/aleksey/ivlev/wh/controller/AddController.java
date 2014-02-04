package aleksey.ivlev.wh.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import aleksey.ivlev.wh.converter.Converter;
import aleksey.ivlev.wh.dto.ProductDto;
import aleksey.ivlev.wh.managers.DicStoresManager;
import aleksey.ivlev.wh.managers.InStockManager;
import aleksey.ivlev.wh.managers.IncomeDetailsManager;
import aleksey.ivlev.wh.managers.IncomesManager;
import aleksey.ivlev.wh.managers.ProductManager;
import aleksey.ivlev.wh.model.DicStores;
import aleksey.ivlev.wh.model.IncomeDetails;
import aleksey.ivlev.wh.model.Incomes;
import aleksey.ivlev.wh.model.Product;
import aleksey.ivlev.wh.utils.AddingProductValidator;

@Controller
public class AddController {

	@Autowired
	private ProductManager productManager;

	@Autowired
	private IncomesManager incomesManager;

	@Autowired
	private IncomeDetailsManager incomeDetManager;

	@Autowired
	private DicStoresManager dicStoresManager;

	@Autowired
	private InStockManager instockManager;

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public void setIncomesManager(IncomesManager incomesManager) {
		this.incomesManager = incomesManager;
	}

	public void setIncomeDetManager(IncomeDetailsManager incomeDetManager) {
		this.incomeDetManager = incomeDetManager;
	}

	public void setDicStoresManager(DicStoresManager dicStoresManager) {
		this.dicStoresManager = dicStoresManager;
	}

	public void setInstockManager(InStockManager instockManager) {
		this.instockManager = instockManager;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(value = "/addProducts", method = RequestMethod.GET)
	public String addProducts(ModelMap map) {
		map.addAttribute("productDto", new ProductDto());
		return "addProducts";
	}

	@RequestMapping(value = "/addProducts.add", method = RequestMethod.POST)
	public String addProduct(
			@Valid @ModelAttribute(value = "productDto") ProductDto productDto,
			BindingResult result, ModelMap map) {
		AddingProductValidator validator = new AddingProductValidator();
		validator.validate(productDto, result);
		if (result.hasErrors()) {
			map.addAttribute("productsList", productManager.getProducts());
			map.addAttribute("dicStores", dicStoresManager.getAllDicStores());
			return "/addProducts";
		} else {
			DicStores dicStore = dicStoresManager
					.getOrCreateDicStores(productDto.getStorName());
			Incomes inc = new Converter().convertToIncomes(productDto, dicStore);
			incomesManager.addIncomes(inc);
			Product product = productManager.getOrCreateProduct(
					productDto.getProdName(), productDto.getProdDescription());
			IncomeDetails incDet = incomeDetManager.convertToIncomeDetails(
					product, productDto.getIncdPrice(),
					productDto.getIncdCount(), inc);
			incomeDetManager.addIncomeDetails(incDet);
			instockManager.createOrUpdateInStock(product, dicStore,
					productDto.getIncdCount());
			return "redirect:/";
		}
	}
}
