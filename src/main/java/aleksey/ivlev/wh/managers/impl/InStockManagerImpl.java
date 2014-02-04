package aleksey.ivlev.wh.managers.impl;

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
	public  InStock getInstock(Product product, DicStores dicStores) {
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

	@Override
	public void createOrUpdateInStock(Product product, DicStores dicStores, Long incdCount) {
		InStock editableInStock = getInstock(product, dicStores);
		if(editableInStock == null){
			InStock inStock = new InStock(product, dicStores, incdCount);
			addInstock(inStock);
		}else{
			editableInStock.setStorCount(editableInStock.getStorCount() + incdCount);
			editInstock(editableInStock);
		}
		
	}

	@Override
	public void updateInStock(Product product, DicStores dicStores,
			Long outdCount) {
		InStock editableInStock = getInstock(product, dicStores);
		editableInStock.setStorCount(editableInStock.getStorCount() - outdCount);
		editInstock(editableInStock);
		
	}

}
