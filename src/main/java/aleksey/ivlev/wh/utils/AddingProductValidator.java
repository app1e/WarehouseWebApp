package aleksey.ivlev.wh.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import aleksey.ivlev.wh.model.Report;

@Component
public class AddingProductValidator implements Validator {

	
	
	@Override
	public boolean supports(Class clazz) {
		return Report.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Locale locale = new Locale("en");
		ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "incomes.incSupplierName", "report.incomes.incSupplierName", new Object[]{messages.getString("label.incSupplierName")});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "incomeDetails.incdCount", "NotEmpty.report.incomeDetails.incdCount", new Object[]{messages.getString("label.incdCount")});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "incomeDetails.incdPrice", "NotEmpty.report.incomeDetails.incdPrice", new Object[]{messages.getString("label.incdPrice")});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product.prodName", "NotEmpty.report.product.prodName", new Object[]{messages.getString("label.prodName")});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dicStores.storName", "NotEmpty.report.dicStores.storName", new Object[]{messages.getString("label.storName")});
		
		Report report = (Report)obj;
		
		
		if(report.getIncomeDetails().getIncdPrice() != null){
			if(report.getIncomeDetails().getIncdPrice() < 0L
					|| report.getIncomeDetails().getIncdPrice() == 0L){
				errors.rejectValue("incomeDetails.incdPrice", "report.incomeDetails.incdPrice");
			}
		}
		
		if(report.getIncomeDetails().getIncdCount() != null){
			if(report.getIncomeDetails().getIncdCount() < 0L
					|| report.getIncomeDetails().getIncdCount() == 0L){
				errors.rejectValue("incomeDetails.incdCount", "report.incomeDetails.incdCount");
			}
		} 
				

	}

}
