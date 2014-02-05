package aleksey.ivlev.wh.managers;

import aleksey.ivlev.wh.model.IncomeDetails;
import aleksey.ivlev.wh.model.Incomes;
import aleksey.ivlev.wh.model.Product;

public interface IncomeDetailsManager {

	void addIncomeDetails(IncomeDetails incomeDetails);

    Long getIncProdIdWithIncId (Product product, Incomes incomes);
	
}
