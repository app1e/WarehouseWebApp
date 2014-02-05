package aleksey.ivlev.wh.managers;

import java.util.List;

import aleksey.ivlev.wh.model.Product;

public interface ProductManager {
	
	void addProduct(Product product);

	Product getProduct(String prodName);

	List<Product> getProducts();
	
	List<String> getProdName();
	
	List<Object> getReports();
	
	Product getOrCreateProduct(String prodName, String prodDescription);
}
