package aleksey.ivlev.wh.hibernate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import aleksey.ivlev.wh.hibernate.OutcomesDao;
import aleksey.ivlev.wh.model.Outcomes;

@Repository
public class OutcomesDaoImpl implements OutcomesDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Override
	public void addOutcomes(Outcomes outcomes) {
		hibernateTemplate.save(outcomes);

	}

}
