package aleksey.ivlev.wh.managers.impl;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aleksey.ivlev.wh.hibernate.ProductDao;
import aleksey.ivlev.wh.managers.ProductManager;
import aleksey.ivlev.wh.model.Product;
import aleksey.ivlev.wh.model.Report;

@Service
public class ProductManagerImpl implements ProductManager {
	
	@Autowired
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);

	}

	@Override
	public Product getProduct(String prodName) {
		return productDao.getProduct(prodName);
	}

	@Override
	public List<Product> getProducts() {
		return productDao.getProducts();
	}

	@Override
	public List<String> getProdName() {
		return productDao.getProdName();
	}
	
	@Override
	public List<Object> getReports() {
		return productDao.getReports();
	}
//	
//	@Override
//	@Transactional
//	public ResultSet getReport(){
//		return productDao.getReport();
//	}
	
	
	

}
