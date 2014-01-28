package aleksey.ivlev.wh.hibernate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import aleksey.ivlev.wh.hibernate.OutcomeDetailsDao;
import aleksey.ivlev.wh.model.OutcomeDetails;

@Repository
public class OutcomeDetailsDaoImpl implements OutcomeDetailsDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Override
	public void addOutcomeDetails(OutcomeDetails outcomeDetails) {
		hibernateTemplate.save(outcomeDetails);
	}

}
