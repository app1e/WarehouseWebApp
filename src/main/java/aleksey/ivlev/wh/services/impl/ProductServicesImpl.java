package aleksey.ivlev.wh.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aleksey.ivlev.wh.hibernate.ProductDao;
import aleksey.ivlev.wh.model.Product;
import aleksey.ivlev.wh.services.ProductService;

@Service
public class ProductServicesImpl implements ProductService{
	@Autowired
	private ProductDao productDao;
	
	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);
		
	}

	@Override
	public int getProduct(String prodName) {
		return productDao.getProduct(prodName);
	}

	@Override
	public List<Product> getProducts() {
		return productDao.getProducts();
	}

}
