package aleksey.ivlev.wh.hibernate.impl;

import java.math.BigDecimal;
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

import aleksey.ivlev.wh.hibernate.DicStoresDao;
import aleksey.ivlev.wh.model.DicStores;

@Repository
public class DicStoresDaoImpl implements DicStoresDao {
 
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void addDicStores(DicStores dicStores) {
		hibernateTemplate.save(dicStores);

	}

	@SuppressWarnings("unchecked")
	@Override
	public DicStores getDicStore(final String storName) {

		return (DicStores) hibernateTemplate
				.execute(new HibernateCallback<DicStores>() {

					@Override
					public DicStores doInHibernate(Session session)
							throws HibernateException, SQLException {
						List<DicStores> listDicStores = new LinkedList<DicStores>();
						Query query = session.createQuery(
										"from DicStores d where LOWER(d.storName) LIKE :storName")
										.setParameter("storName", storName.toLowerCase());
						listDicStores = query.list();
						if (listDicStores == null || listDicStores.isEmpty()) {
							return null;

						} else {
							return listDicStores.get(0);
						}
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DicStores> getAllDicStores() {
		return hibernateTemplate.execute(new HibernateCallback<List<DicStores>>() {

			@Override
			public List<DicStores> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from DicStores order by storName asc");
				return query.list();
			}
		});	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DicStores> getIdAllDicStores() {
		return hibernateTemplate.execute(new HibernateCallback<List<DicStores>>() {

			@Override
			public List<DicStores> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("Select d.storId from DicStores d");
				return query.list();
			}
		});	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DicStores> getReports() {
		return hibernateTemplate.execute(new HibernateCallback<List<DicStores>>() {

			@Override
			public List<DicStores> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from DicStores");
				return query.list();
			}
		});	
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getPrice(final String prodName, final String storName) {
		return hibernateTemplate.execute(new HibernateCallback<Long>() {

			@Override
			public Long doInHibernate(Session session) throws HibernateException,
					SQLException {				
				Query query = session.createSQLQuery(
						"SELECT (SUM(DECODE(t.stock_stor_id,(Select ds.stor_id from warehouse.dic_stores ds where ds.stor_name like :storName),"
			                    + "(SELECT SUM(ind.incd_count*ind.incd_price) FROM warehouse.inCOMES i ,"
			                    + " warehouse.inCOME_DETAILS ind WHERE ind.incd_inc_id=i.inc_id"
			                    + " AND i.inc_stor_id   =t.stock_stor_id AND ind.incd_prod_id =t.stock_prod_id"
			                    + " AND i.inc_stor_id=(Select ds.stor_id from warehouse.dic_stores ds where ds.stor_name like :storName)) - (SELECT SUM(od.outd_count*od.outd_price)"
			                    + " FROM warehouse.OUTCOMES o , warehouse.OUTCOME_DETAILS od WHERE od.outd_out_id=o.out_id"
			                    + " AND o.out_stor_id   =t.stock_stor_id AND od.outd_prod_id =t.stock_prod_id"
			                    + " AND o.out_stor_id   =(Select ds.stor_id from warehouse.dic_stores ds where ds.stor_name like "
			                    + ":storName)) ))) / "
			                    + "SUM(DECODE(t.stock_stor_id,(Select ds.stor_id from warehouse.dic_stores ds where ds.stor_name like "
			                    + ":storName),t.stor_count)) FROM warehouse.INSTOCK t, warehouse.dic_stores s,"
			                    + " warehouse.products p WHERE t.stock_prod_id=(Select pr.prod_id from warehouse.products pr where pr.prod_name like "
			                    + ":prodName) AND t.stock_stor_id  ="
			                    + "(Select ds.stor_id from warehouse.dic_stores ds where ds.stor_name like "
			                    + ":storName)")
										.setParameter("storName", storName)
										.setParameter("prodName", prodName);
				List<BigDecimal> values = new LinkedList<BigDecimal>();
				values = query.list();
				if (values == null || values.isEmpty()) {
					return -1L;
				} else {
					return values.get(0).longValue();
				}
			}
		});
		
	}

}
