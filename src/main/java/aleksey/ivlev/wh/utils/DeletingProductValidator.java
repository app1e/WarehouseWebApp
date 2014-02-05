package aleksey.ivlev.wh.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import aleksey.ivlev.wh.dto.ProductDto;
import aleksey.ivlev.wh.managers.InStockManager;

@Component
public class DeletingProductValidator implements Validator {
	
	private InStockManager instockManager;
	
	public DeletingProductValidator(){
		
	}
	public DeletingProductValidator(InStockManager instockManager) {
		this.instockManager = instockManager;
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return ProductDto.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Locale locale = new Locale("en");
		ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "outCustomer", "report.outcomes.outCustomer", new Object[]{messages.getString("label.outCustomer")});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "outdCount", "NotEmpty.report.outcomeDetails.outdCount", new Object[]{messages.getString("label.outdCount")});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prodName", "NotEmpty.report.product.prodName", new Object[]{messages.getString("label.prodName")});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "storName", "NotEmpty.report.dicStores.storName", new Object[]{messages.getString("label.storName")});
		
		ProductDto productDto = (ProductDto)obj;
		
		if(productDto.getOutdCount() != null){
			if(productDto.getOutdCount() < 0L
					|| productDto.getOutdCount() == 0L){
				errors.rejectValue("outdCount", "report.outcomeDetails.outdCount");
			}
			Long prodCount = instockManager.getProductCount(productDto.getProdName(), 
					productDto.getStorName());
			if(prodCount == -1L){
				errors.rejectValue("prodName", "NoData.report.product.prodName");
			}
			if((prodCount - productDto.getOutdCount()) < 0L){
				errors.rejectValue("outdCount", "report.outcomeDetails.outdCount.msg", new Object[]{"asdsd"}, "Default message");
			}
		}			

	}

}
