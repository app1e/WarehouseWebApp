package aleksey.ivlev.wh.managers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aleksey.ivlev.wh.hibernate.InStockDao;
import aleksey.ivlev.wh.managers.InStockManager;
import aleksey.ivlev.wh.model.DicStores;
import aleksey.ivlev.wh.model.InStock;
import aleksey.ivlev.wh.model.Product;

@Service
public class InStockManagerImpl implements InStockManager {
	
	@Autowired
	private InStockDao instockDao;
	
	public void setInstockDao(InStockDao instockDao) {
		this.instockDao = instockDao;
	}

	@Override
	@Transactional
	public void addInstock(InStock instock) {
		instockDao.addInstock(instock);

	}

	@Override
	public  List<InStock> getInstock(Product product, DicStores dicStores) {
		return instockDao.getInstock(product, dicStores);
	}

	@Override
	public void editInstock(InStock instock) {
		instockDao.editInstock(instock);

	}

	@Override
	public Long getProductCount(String prodName, String storName) {
		return instockDao.getProductCount(prodName, storName);
	}

}
