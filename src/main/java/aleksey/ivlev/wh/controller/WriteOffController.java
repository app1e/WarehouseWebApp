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

import aleksey.ivlev.wh.dto.ProductDto;
import aleksey.ivlev.wh.managers.DicStoresManager;
import aleksey.ivlev.wh.managers.InStockManager;
import aleksey.ivlev.wh.managers.OutcomeDetailsManager;
import aleksey.ivlev.wh.managers.OutcomesManager;
import aleksey.ivlev.wh.managers.ProductManager;
import aleksey.ivlev.wh.model.DicStores;
import aleksey.ivlev.wh.model.OutcomeDetails;
import aleksey.ivlev.wh.model.Outcomes;
import aleksey.ivlev.wh.model.Product;
import aleksey.ivlev.wh.utils.DeletingProductValidator;

@Controller
public class WriteOffController {
	
	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private DicStoresManager dicStoresManager;
	
	@Autowired
	private OutcomesManager outcomesManager;
	
	@Autowired
	private OutcomeDetailsManager outcomeDetManager;
	
	@Autowired
	private InStockManager instockManager;
	
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
	public void setDicStoresManager(DicStoresManager dicStoresManager) {
		this.dicStoresManager = dicStoresManager;
	}

	public void setOutcomesManager(OutcomesManager outcomesManager) {
		this.outcomesManager = outcomesManager;
	}

	public void setOutcomeDetManager(OutcomeDetailsManager outcomeDetManager) {
		this.outcomeDetManager = outcomeDetManager;
	}

	public void setInstockManager(InStockManager instockManager) {
		this.instockManager = instockManager;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value = "/writeOff", method = RequestMethod.GET)
    public String deleteProducts(ModelMap map)
    {
		map.addAttribute("productDto", new ProductDto());
		map.addAttribute("dicStores", dicStoresManager.getAllDicStores());
		map.addAttribute("products", productManager.getProducts());
        return "writeOff";
    }
	
	@RequestMapping(value = "/writeOff.delete", method = RequestMethod.POST)
	public String deleteProduct(
			@Valid @ModelAttribute(value = "productDto") ProductDto productDto,
			BindingResult result) {
		DeletingProductValidator deletingValidator = new DeletingProductValidator(
				instockManager);
		deletingValidator.validate(productDto, result);
		if (result.hasErrors()) {
			return "/writeOff";
		} else {
			DicStores dicStore = dicStoresManager.getDicStore(productDto
					.getStorName());
			Outcomes outcomes = outcomesManager.convertToOutcomes(
					productDto.getOutDate(), productDto.getOutCustomer(),
					dicStore);
			outcomesManager.addOutcomes(outcomes);
			Product product = productManager.getProduct(productDto
					.getProdName());
			Long price = dicStoresManager.getPrice(productDto.getProdName(),
					productDto.getStorName());
			OutcomeDetails outcomeDetails = outcomeDetManager
					.convertToOutcomeDetails(outcomes, product,
							productDto.getOutdCount(), price);
			outcomeDetManager.addOutcomeDetails(outcomeDetails);
			instockManager.updateInStock(product, dicStore,
					productDto.getOutdCount());

			return "redirect:/";
		}
	}

}
