package aleksey.ivlev.wh.managers;

import java.util.Date;

import aleksey.ivlev.wh.model.DicStores;
import aleksey.ivlev.wh.model.Outcomes;

public interface OutcomesManager {
	
	void addOutcomes (Outcomes outcomes);
	
	Outcomes convertToOutcomes(Date outDate, String outCustomer, DicStores dicStore);
}
