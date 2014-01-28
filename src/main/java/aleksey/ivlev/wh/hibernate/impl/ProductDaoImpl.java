package aleksey.ivlev.wh.hibernate.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import aleksey.ivlev.wh.hibernate.ProductDao;
import aleksey.ivlev.wh.model.DicStores;
import aleksey.ivlev.wh.model.Product;
import aleksey.ivlev.wh.model.Report;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void addProduct(Product product) {
		hibernateTemplate.save(product);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Product getProduct(final String prodName) {
		return hibernateTemplate.execute(new HibernateCallback<Product>() {

			@Override
			public Product doInHibernate(Session session)
					throws HibernateException, SQLException {

				List<Product> prodList = new LinkedList<Product>();
				Query query = session.createQuery("from Product d where LOWER(d.prodName) LIKE :prodName")
						.setParameter("prodName", prodName.toLowerCase());
				prodList = query.list();
				if (prodList == null || prodList.isEmpty()) {
					return null;
				} else{
					return prodList.get(0);
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts() {
		return hibernateTemplate.executeFind(new HibernateCallback<List<Product>>() {

			@Override
			public List<Product> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("from Product order by prodId asc");
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getProdName() {
		return hibernateTemplate.execute(new HibernateCallback<List<String>>() {

			@Override
			public List<String> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("SELECT p.prodName FROM product p order by p.prodName asc");
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Object> getReports(){
		
		return hibernateTemplate.execute(new HibernateCallback<List<Object>>() {

			@Override
			public List<Object> doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<DicStores> dicStores = new LinkedList<DicStores>();
				Query dicStoresQuery = session.createQuery("from DicStores");
				dicStores = dicStoresQuery.list();
				if(!dicStores.isEmpty() || dicStores != null){
					Map<Integer, String> dsMap = new TreeMap<Integer, String>();
					for(DicStores ds : dicStores){
						int id = ds.getStorId().intValue();
						String name = ds.getStorName();
						dsMap.put(id, name);
					}
					StringBuilder builder = new StringBuilder();
					builder.append("SELECT p.prod_name as NAME,");
					for(Map.Entry<Integer, String> entry : dsMap.entrySet()) {
						builder.append("SUM(DECODE(t.stock_stor_id,"
				                   + entry.getKey() + ",t.stor_count))  || p.prod_description ||"
				                   + "';  ' || SUM(DECODE(t.stock_stor_id,"
				                   + entry.getKey() + ",(SELECT SUM(ind.incd_count*ind.incd_price) FROM warehouse.INCOMES i ,"
				                   + "warehouse.INCOME_DETAILS ind WHERE ind.incd_inc_id=i.inc_id"
				                   + " AND i.inc_stor_id   =t.stock_stor_id AND ind.incd_prod_id =t.stock_prod_id"
				                   + " AND i.inc_stor_id="
				                   + entry.getKey() + ")- (SELECT SUM(od.outd_count*od.outd_price)"
				                   + " FROM warehouse.OUTCOMES o ,"
				                   + " warehouse.OUTCOME_DETAILS od"
				                   + " WHERE od.outd_out_id=o.out_id"
				                   + " AND o.out_stor_id   =t.stock_stor_id"
				                   + " AND od.outd_prod_id =t.stock_prod_id"
				                   + " AND o.out_stor_id   ="
				                   + entry.getKey() + ") )) || 'grn' AS \""
				                   + entry.getValue() + "\",");
				       }
						String string = builder.substring(0, builder.length() - 1);
						string += " FROM warehouse.INSTOCK t,"
				               + " warehouse.DIC_STORES s,"
				               + " warehouse.PRODUCTS p WHERE t.stock_prod_id=p.prod_id "
				               + "AND t.stock_stor_id  =s.stor_id "
				               + "GROUP BY p.prod_id, p.prod_description, p.prod_name ORDER BY p.prod_name";
						System.out.println(string);
						List<Object> reportList = new ArrayList<Object>(dicStores.size());
						reportList = session.createSQLQuery(string).list();
						return reportList;
				}else{
					return null;
				}
				
					
			}
		});
		
		
		
	}
//	
//	@SuppressWarnings("deprecation")
//	@Override
//	public ResultSet getReport() {
//		Connection con = null;
//	    Statement st1 = null;
//	    ResultSet rs = null;
//		Map<Integer, String> dsMap = new TreeMap<Integer, String>();
//		String sqlStr = "Select stor_id, stor_name from warehouse.dic_stores order by stor_name asc";
//		con = this.sessionFactory.getCurrentSession().connection();
//		try {
//			st1 = con.createStatement();
//			rs = st1.executeQuery(sqlStr);
//	        while (rs.next()) {
//	            int id = rs.getInt(1);
//	            String name = rs.getString(2);
//	            dsMap.put(id, name);
//	        }
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		String s = "SELECT p.prod_name as \" \",";
//        for(Map.Entry<Integer, String> entry : dsMap.entrySet()) {
//           s += "SUM(DECODE(t.stock_stor_id,"
//                   + entry.getKey() + ",t.stor_count))  || p.prod_description ||"
//                   + "';  ' || SUM(DECODE(t.stock_stor_id,"
//                   + entry.getKey() + ",(SELECT SUM(ind.incd_count*ind.incd_price) FROM warehouse.INCOMES i ,"
//                   + "warehouse.INCOME_DETAILS ind WHERE ind.incd_inc_id=i.inc_id"
//                   + " AND i.inc_stor_id   =t.stock_stor_id AND ind.incd_prod_id =t.stock_prod_id"
//                   + " AND i.inc_stor_id="
//                   + entry.getKey() + ")- (SELECT SUM(od.outd_count*od.outd_price)"
//                   + " FROM warehouse.OUTCOMES o ,"
//                   + " warehouse.OUTCOME_DETAILS od"
//                   + " WHERE od.outd_out_id=o.out_id"
//                   + " AND o.out_stor_id   =t.stock_stor_id"
//                   + " AND od.outd_prod_id =t.stock_prod_id"
//                   + " AND o.out_stor_id   ="
//                   + entry.getKey() + ") )) || 'grn' \""
//                   + entry.getValue() + "\",";
//       }
//       String substr = s.substring(0, s.length() - 1);
//       substr += " FROM warehouse.INSTOCK t,"
//               + " warehouse.DIC_STORES s,"
//               + " warehouse.PRODUCT p WHERE t.stock_prod_id=p.prod_id "
//               + "AND t.stock_stor_id  =s.stor_id "
//               + "GROUP BY p.prod_id, p.prod_description, p.prod_name ORDER BY p.prod_name";
//       System.out.println(substr);
//		ResultSet rs2 = null;
//		try {
//			st1 = con.createStatement();
//			rs2 = st1.executeQuery(substr);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(rs2);
//		return rs2;
//		
//	}

	

}
