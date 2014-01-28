package aleksey.ivlev.wh.hibernate;

import java.sql.ResultSet;
import java.util.List;


import aleksey.ivlev.wh.model.Product;
import aleksey.ivlev.wh.model.Report;

public interface ProductDao {

	void addProduct(Product product);

	Product getProduct(String prodName);
	
	List<Product> getProducts();
	
	List<String> getProdName();
	
	List<Object> getReports();
//
//	ResultSet getReport();

}
