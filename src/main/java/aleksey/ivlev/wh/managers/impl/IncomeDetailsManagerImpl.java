package aleksey.ivlev.wh.managers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aleksey.ivlev.wh.hibernate.IncomeDetailsDao;
import aleksey.ivlev.wh.managers.IncomeDetailsManager;
import aleksey.ivlev.wh.model.IncomeDetails;
import aleksey.ivlev.wh.model.Incomes;
import aleksey.ivlev.wh.model.Product;

@Service
public class IncomeDetailsManagerImpl implements IncomeDetailsManager {
	
	@Autowired
	private IncomeDetailsDao incomeDetDao;
	
	public void setIncomeDetDao(IncomeDetailsDao incomeDetDao) {
		this.incomeDetDao = incomeDetDao;
	}

	@Override
	public void addIncomeDetails(IncomeDetails incomeDetails) {
		incomeDetDao.addIncomeDetails(incomeDetails);

	}

	@Override
	public Long getIncProdIdWithIncId(Product product, Incomes incomes) {
		return incomeDetDao.getIncProdIdWithIncId(product, incomes);
	}

}
