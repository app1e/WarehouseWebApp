package aleksey.ivlev.wh.converter;

import aleksey.ivlev.wh.dto.ProductDto;
import aleksey.ivlev.wh.model.DicStores;
import aleksey.ivlev.wh.model.Incomes;

public class Converter {

	public Incomes convertToIncomes(ProductDto productDto, DicStores dicStores){
		return new Incomes(productDto.getIncDate(), 
				productDto.getIncSupplierName(), dicStores);
	}
}
