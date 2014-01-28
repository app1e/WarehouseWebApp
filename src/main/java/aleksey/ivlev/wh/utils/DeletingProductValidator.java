package aleksey.ivlev.wh.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import aleksey.ivlev.wh.managers.InStockManager;
import aleksey.ivlev.wh.model.Report;

@Component
public class DeletingProductValidator implements Validator {
	
	private InStockManager instockManager;
	
	public DeletingProductValidator(){
		
	}
	public DeletingProductValidator(InStockManager instockManager) {
		this.instockManager = instockManager;
	}
	@Override
	public boolean supports(Class clazz) {
		return Report.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Locale locale = new Locale("en");
		ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "outcomes.outCustomer", "report.outcomes.outCustomer", new Object[]{messages.getString("label.outCustomer")});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "outcomeDetails.outdCount", "NotEmpty.report.outcomeDetails.outdCount", new Object[]{messages.getString("label.outdCount")});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product.prodName", "NotEmpty.report.product.prodName", new Object[]{messages.getString("label.prodName")});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dicStores.storName", "NotEmpty.report.dicStores.storName", new Object[]{messages.getString("label.storName")});
		
		Report report = (Report)obj;
		
		if(report.getOutcomeDetails().getOutdCount() != null){
			if(report.getOutcomeDetails().getOutdCount() < 0L
					|| report.getOutcomeDetails().getOutdCount() == 0L){
				errors.rejectValue("outcomeDetails.outdCount", "report.outcomeDetails.outdCount");
			}
			Long prodCount = instockManager.getProductCount(report.getProduct().getProdName(), 
					report.getDicStores().getStorName());
			if(prodCount == -1L){
				errors.rejectValue("outcomeDetails.outdCount", "NoData.report.outcomeDetails.outdCount");
			}
			if((prodCount - report.getOutcomeDetails().getOutdCount()) < 0L){
				System.out.println("true");
				errors.rejectValue("outcomeDetails.outdCount", "Size.report.outcomeDetails.outdCount", "Default message");
			}
		}			

	}

}
