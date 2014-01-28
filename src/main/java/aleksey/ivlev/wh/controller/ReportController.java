package aleksey.ivlev.wh.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import aleksey.ivlev.wh.managers.DicStoresManager;
import aleksey.ivlev.wh.managers.InStockManager;
import aleksey.ivlev.wh.managers.IncomeDetailsManager;
import aleksey.ivlev.wh.managers.IncomesManager;
import aleksey.ivlev.wh.managers.OutcomeDetailsManager;
import aleksey.ivlev.wh.managers.OutcomesManager;
import aleksey.ivlev.wh.managers.ProductManager;
import aleksey.ivlev.wh.model.DicStores;
import aleksey.ivlev.wh.model.InStock;
import aleksey.ivlev.wh.model.IncomeDetails;
import aleksey.ivlev.wh.model.Incomes;
import aleksey.ivlev.wh.model.OutcomeDetails;
import aleksey.ivlev.wh.model.Outcomes;
import aleksey.ivlev.wh.model.Product;
import aleksey.ivlev.wh.model.Report;
import aleksey.ivlev.wh.utils.AddingProductValidator;
import aleksey.ivlev.wh.utils.DeletingProductValidator;

@Controller
public class ReportController {
	
	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private IncomesManager incomesManager;
	
	@Autowired
	private IncomeDetailsManager incomeDetManager;
	
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

	public void setIncomesManager(IncomesManager incomesManager) {
		this.incomesManager = incomesManager;
	}

	public void setIncomeDetManager(IncomeDetailsManager incomeDetManager) {
		this.incomeDetManager = incomeDetManager;
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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String listProducts(ModelMap map)
    {
        map.addAttribute("productsList", productManager.getProducts());
        map.addAttribute("dicStores", dicStoresManager.getAllDicStores());
        map.addAttribute("list",productManager.getReports());
        return "report";
    }
	
	@RequestMapping(value = "/addProducts", method = RequestMethod.GET)
    public String addProducts(ModelMap map)
    {	
		map.addAttribute("report", new Report());
        return "addProducts";
    }
	
	@RequestMapping(value = "/addProducts.add", method = RequestMethod.POST)
    public String addProduct(@Valid @ModelAttribute(value="report") Report report, BindingResult result)
    {	
		AddingProductValidator validator = new AddingProductValidator();
		validator.validate(report, result);
		if(result.hasErrors()){
    	return "/addProducts";
    	}else{
    		DicStores dicStore = dicStoresManager.getDicStore(report.getDicStores().getStorName());
    		if(dicStore == null){
    			dicStore = new DicStores(report.getDicStores().getStorName());
    			dicStoresManager.addDicStores(dicStore);
    		} 
    		Outcomes outcomes = new Outcomes(dicStore);
			outcomesManager.addOutcomes(outcomes);
			
    		Incomes inc = new Incomes(report.getIncomes().getIncDate(), report.getIncomes().getIncSupplierName(), dicStore);
			incomesManager.addIncomes(inc);
    		
			

    		Product product = productManager.getProduct(report.getProduct().getProdName());
    		if(product == null){
    			product = report.getProduct();
    			productManager.addProduct(product);
    		}
    		IncomeDetails incDet = new IncomeDetails(product, report.getIncomeDetails().getIncdPrice(), report.getIncomeDetails().getIncdCount(), inc);
			incomeDetManager.addIncomeDetails(incDet);
			OutcomeDetails outComeDet = new OutcomeDetails(outcomes, product, new BigDecimal(0).longValue(), new BigDecimal(0).longValue());
			outcomeDetManager.addOutcomeDetails(outComeDet);
			
    		List<InStock> listInStock = instockManager.getInstock(product, dicStore);
    		InStock inStock = null;
    		if(listInStock == null || listInStock.isEmpty()){
    			inStock = new InStock(product, dicStore, report.getIncomeDetails().getIncdCount());
    			instockManager.addInstock(inStock);
    		}else{
    			InStock inStockTmp = new InStock();
    			inStockTmp.setStockId(listInStock.get(0).getStockId());
    			inStockTmp.setProduct(listInStock.get(0).getProduct());
    			inStockTmp.setDicStores(listInStock.get(0).getDicStores());
    			inStockTmp.setStorCount(listInStock.get(0).getStorCount() + report.getIncomeDetails().getIncdCount());
    			instockManager.editInstock(inStockTmp);
    		}
    		return "redirect:/";
    	}
    }
	
	@RequestMapping(value = "/writeOff", method = RequestMethod.GET)
    public String deleteProducts(ModelMap map)
    {
		map.addAttribute("report", new Report());
		map.addAttribute("dicStores", dicStoresManager.getAllDicStores());
		map.addAttribute("products", productManager.getProducts());
        return "writeOff";
    }
	
	@RequestMapping(value = "/writeOff.delete", method = RequestMethod.POST)
    public String deleteProduct(@Valid @ModelAttribute(value="report") Report report, BindingResult result)
    {
		DeletingProductValidator deletingValidator = new DeletingProductValidator(instockManager);
		deletingValidator.validate(report, result);
		if(result.hasErrors()){
			return "/writeOff";
    	}else{
			DicStores dicStore = dicStoresManager.getDicStore(report.getDicStores().getStorName());
			Outcomes outcomes = new Outcomes(report.getOutcomes().getOutDate(), report.getOutcomes().getOutCustomer(), dicStore);
			outcomesManager.addOutcomes(outcomes);
			Product product = productManager.getProduct(report.getProduct().getProdName());
			Long price = dicStoresManager.getPrice(report.getProduct().getProdName(), report.getDicStores().getStorName());
			OutcomeDetails outcomeDetails = new OutcomeDetails(outcomes, product, report.getOutcomeDetails().getOutdCount(), price);
	        outcomeDetManager.addOutcomeDetails(outcomeDetails);
	        List<InStock> listInStock = instockManager.getInstock(product, dicStore);
			InStock inStockTmp = new InStock();
			inStockTmp.setStockId(listInStock.get(0).getStockId());
			inStockTmp.setProduct(listInStock.get(0).getProduct());
			inStockTmp.setDicStores(listInStock.get(0).getDicStores());
			inStockTmp.setStorCount(listInStock.get(0).getStorCount() - report.getOutcomeDetails().getOutdCount());
			instockManager.editInstock(inStockTmp);
			System.out.println(price);
			
			return "redirect:/";
    	}
    }

}
