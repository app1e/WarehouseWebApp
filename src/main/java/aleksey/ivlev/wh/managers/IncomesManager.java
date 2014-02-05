package aleksey.ivlev.wh.managers;

import java.util.Date;

import aleksey.ivlev.wh.model.DicStores;
import aleksey.ivlev.wh.model.Incomes;

public interface IncomesManager {
	
	void addIncomes(Incomes incomes);
	
	Incomes convertToIncomes(Date incDate, String incSupplierName, DicStores dicStore);
}
