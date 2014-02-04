package aleksey.ivlev.wh.managers;

import aleksey.ivlev.wh.model.OutcomeDetails;
import aleksey.ivlev.wh.model.Outcomes;
import aleksey.ivlev.wh.model.Product;

public interface OutcomeDetailsManager {
	
	void addOutcomeDetails(OutcomeDetails outcomeDetails);
	
	OutcomeDetails convertToOutcomeDetails(Outcomes outcomes, 
			Product product, Long outdCount, Long outdPrice);
}
