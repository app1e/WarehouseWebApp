package aleksey.ivlev.wh.managers.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aleksey.ivlev.wh.hibernate.OutcomesDao;
import aleksey.ivlev.wh.managers.OutcomesManager;
import aleksey.ivlev.wh.model.DicStores;
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
	@Override
	public Outcomes convertToOutcomes(Date outDate, String outCustomer, DicStores dicStore) {
		return new Outcomes(outDate, outCustomer, dicStore);
	}

}
