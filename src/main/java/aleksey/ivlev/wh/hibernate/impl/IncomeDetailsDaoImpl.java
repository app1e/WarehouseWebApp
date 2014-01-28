package aleksey.ivlev.wh.hibernate.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import aleksey.ivlev.wh.hibernate.IncomeDetailsDao;
import aleksey.ivlev.wh.model.IncomeDetails;
import aleksey.ivlev.wh.model.Incomes;
import aleksey.ivlev.wh.model.Product;

@Repository
public class IncomeDetailsDaoImpl implements IncomeDetailsDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Override
	public void addIncomeDetails(IncomeDetails incomeDetails) {
		hibernateTemplate.save(incomeDetails);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getIncProdIdWithIncId(final Product product, final Incomes incomes) {
		return hibernateTemplate.execute(new HibernateCallback<Long>() {

			@Override
			public Long doInHibernate(Session session) throws HibernateException,
					SQLException {
				List<IncomeDetails> incDetList = new LinkedList<IncomeDetails>();
				Query query = session.createQuery(
						"select i from IncomeDetails i where i.product = :product and i.incomes = :incomes")
						.setParameter("product", product)
						.setParameter("incoes", incomes);
				incDetList = query.list();
				if (incDetList == null || incDetList.isEmpty()) {
					return 0L;
				} else{
					return incDetList.get(0).getIncdId();
				}
			}
		});
	}

}
