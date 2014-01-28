package aleksey.ivlev.wh.hibernate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import aleksey.ivlev.wh.hibernate.IncomesDao;
import aleksey.ivlev.wh.model.Incomes;

@Repository
public class IncomesDaoImpl implements IncomesDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Override
	public void addIncomes(Incomes incomes) {
		hibernateTemplate.save(incomes);
	}

}
