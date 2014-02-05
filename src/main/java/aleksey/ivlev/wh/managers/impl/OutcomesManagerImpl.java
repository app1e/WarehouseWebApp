package aleksey.ivlev.wh.managers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aleksey.ivlev.wh.hibernate.OutcomesDao;
import aleksey.ivlev.wh.managers.OutcomesManager;
import aleksey.ivlev.wh.model.Outcomes;

@Service
public class OutcomesManagerImpl implements OutcomesManager {
	
	@Autowired
	private OutcomesDao	outcomesDao;
	
	public void setOutcomesDao(OutcomesDao outcomesDao) {
		this.outcomesDao = outcomesDao;
	}
	@Override
	public void addOutcomes(Outcomes outcomes) {
		outcomesDao.addOutcomes(outcomes);

	}

}
