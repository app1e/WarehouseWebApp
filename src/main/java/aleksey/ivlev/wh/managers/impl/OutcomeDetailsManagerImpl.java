package aleksey.ivlev.wh.managers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aleksey.ivlev.wh.hibernate.OutcomeDetailsDao;
import aleksey.ivlev.wh.managers.OutcomeDetailsManager;
import aleksey.ivlev.wh.model.OutcomeDetails;
import aleksey.ivlev.wh.model.Outcomes;
import aleksey.ivlev.wh.model.Product;

@Service
public class OutcomeDetailsManagerImpl implements OutcomeDetailsManager {

	@Autowired
	private OutcomeDetailsDao outcomeDetDao;
	
	public void setOutcomeDetDao(OutcomeDetailsDao outcomeDetDao) {
		this.outcomeDetDao = outcomeDetDao;
	}

	@Override
	public void addOutcomeDetails(OutcomeDetails outcomeDetails) {
		outcomeDetDao.addOutcomeDetails(outcomeDetails);

	}

	@Override
	public OutcomeDetails convertToOutcomeDetails(Outcomes outcomes,
			Product product, Long outdCount, Long outdPrice) {
		return new OutcomeDetails(outcomes, product, outdCount, outdPrice);
	}

}
