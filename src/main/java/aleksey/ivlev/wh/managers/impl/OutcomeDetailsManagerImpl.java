package aleksey.ivlev.wh.managers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aleksey.ivlev.wh.hibernate.OutcomeDetailsDao;
import aleksey.ivlev.wh.managers.OutcomeDetailsManager;
import aleksey.ivlev.wh.model.OutcomeDetails;

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

}
