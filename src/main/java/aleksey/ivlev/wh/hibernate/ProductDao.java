package aleksey.ivlev.wh.hibernate;

import java.util.List;

import aleksey.ivlev.wh.model.Product;

public interface ProductDao {
	
	void addProduct(Product product);
	
	int getProduct(String prodName);
	
	List<Product> getProducts();
	
	
}
