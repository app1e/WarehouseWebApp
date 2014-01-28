package aleksey.ivlev.wh.hibernate;

import aleksey.ivlev.wh.model.IncomeDetails;
import aleksey.ivlev.wh.model.Incomes;
import aleksey.ivlev.wh.model.Product;

public interface IncomeDetailsDao {
	
	void addIncomeDetails(IncomeDetails incomeDetails);

    Long getIncProdIdWithIncId (Product product, Incomes incomes);
}
