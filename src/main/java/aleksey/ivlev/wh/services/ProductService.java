package aleksey.ivlev.wh.services;

import java.util.List;

import aleksey.ivlev.wh.model.Product;

public interface ProductService {

	void addProduct(Product product);

	int getProduct(String prodName);

	List<Product> getProducts();

}
