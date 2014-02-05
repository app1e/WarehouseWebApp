package aleksey.ivlev.wh.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import aleksey.ivlev.wh.dto.ProductDto;

@Component
public class AddingProductValidator implements Validator {

	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ProductDto.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Locale locale = new Locale("en");
		ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "incSupplierName", "report.incomes.incSupplierName", new Object[]{messages.getString("label.incSupplierName")});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "incdCount", "NotEmpty.report.incomeDetails.incdCount", new Object[]{messages.getString("label.incdCount")});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "incdPrice", "NotEmpty.report.incomeDetails.incdPrice", new Object[]{messages.getString("label.incdPrice")});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prodName", "NotEmpty.report.product.prodName", new Object[]{messages.getString("label.prodName")});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "storName", "NotEmpty.report.dicStores.storName", new Object[]{messages.getString("label.storName")});
		
		ProductDto productDto = (ProductDto)obj;
		
		
		if(productDto.getIncdPrice() != null){
			if(productDto.getIncdPrice() < 0L
					|| productDto.getIncdPrice() == 0L){
				errors.rejectValue("incdPrice", "report.incomeDetails.incdPrice");
			}
		}
		
		if(productDto.getIncdCount() != null){
			if(productDto.getIncdCount() < 0L
					|| productDto.getIncdCount() == 0L){
				errors.rejectValue("incdCount", "report.incomeDetails.incdCount");
			}
		} 
				

	}

}
