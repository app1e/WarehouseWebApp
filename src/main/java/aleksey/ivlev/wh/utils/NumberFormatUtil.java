package aleksey.ivlev.wh.utils;

import java.beans.PropertyEditorSupport;

import org.springframework.web.bind.WebDataBinder;

public class NumberFormatUtil {
	public static void registerLongFormat (WebDataBinder binder) {
	    binder.registerCustomEditor(Long.class, new CustomerLongEditor());
	}

	private static class CustomerLongEditor extends PropertyEditorSupport{    
	    public String getAsText() { 
	        Long l = (Long) getValue(); 
	        if(l != null){
	        	return l.toString(); 
	        }else{
	        	return ""; 
	        }
	        
	    } 

	    public void setAsText(String str) throws IllegalArgumentException{
	    	if(!str.matches("\\d+") || str == null){
	    		setValue(0);
	    	}else{
	    		setValue(Long.parseLong(str));
	    	}
//	        if(str == null || ("").equals(str) || str.length() == 0) {
//	            setValue(0); 
//	        } else {
//	            setValue(Long.parseLong(str)); 
//	        }
	   } 
	}
}
