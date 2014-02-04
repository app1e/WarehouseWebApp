package aleksey.ivlev.wh.managers.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aleksey.ivlev.wh.hibernate.IncomesDao;
import aleksey.ivlev.wh.managers.IncomesManager;
import aleksey.ivlev.wh.model.DicStores;
import aleksey.ivlev.wh.model.Incomes;

@Service
public class IncomesManagerImpl implements IncomesManager {

	@Autowired
	private IncomesDao incomesDao;

	public void setIncomesDao(IncomesDao incomesDao) {
		this.incomesDao = incomesDao;
	}

	@Override
	public void addIncomes(Incomes incomes) {
		incomesDao.addIncomes(incomes);
	}

	@Override
	public Incomes convertToIncomes(Date incDate, String incSupplierName,
			DicStores dicStore) {
		return new Incomes(incDate, incSupplierName, dicStore);
		
	}

}
