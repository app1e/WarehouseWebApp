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

import aleksey.ivlev.wh.hibernate.InStockDao;
import aleksey.ivlev.wh.model.DicStores;
import aleksey.ivlev.wh.model.InStock;
import aleksey.ivlev.wh.model.Product;

@Repository
public class InStockDaoImpl implements InStockDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void addInstock(InStock instock) {
		hibernateTemplate.save(instock);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InStock> getInstock(final Product product,
			final DicStores dicStores) {
		return hibernateTemplate
				.executeFind(new HibernateCallback<List<InStock>>() {

					@Override
					public List<InStock> doInHibernate(Session session)
							throws HibernateException, SQLException {
						List<InStock> instockList = new LinkedList<InStock>();
						Query query = session
								.createQuery(
										"select i from InStock i where i.product = :product and i.dicStores = :dicStores")
								.setParameter("product", product)
								.setParameter("dicStores", dicStores);
						instockList = query.list();
						if (instockList == null || instockList.isEmpty()) {
							return null;
						} else {
							return instockList;
						}
					}
				});
	}

	@Override
	public void editInstock(InStock instock) {
		hibernateTemplate.update(instock);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getProductCount(final String prodName, final String storName) {
		return hibernateTemplate.execute(new HibernateCallback<Long>() {

			@Override
			public Long doInHibernate(Session session) throws HibernateException,
					SQLException {
				List<InStock> instockList = new LinkedList<InStock>();
				Query query = session.createQuery("SELECT ins FROM InStock ins "
						+ "WHERE ins.product = (SELECT p FROM Product p WHERE p.prodName LIKE :prodName)"
						+ " AND ins.dicStores = (SELECT ds FROM DicStores ds WHERE ds.storName LIKE :storName)")
						.setParameter("prodName", prodName)
						.setParameter("storName", storName);
				instockList = query.list();
				if (instockList == null || instockList.isEmpty()) {
					return -1L;
				} else {
					return instockList.get(0).getStorCount();
				}
			}
		});
	}

}
