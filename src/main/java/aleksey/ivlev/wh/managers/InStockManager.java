package aleksey.ivlev.wh.managers;

import aleksey.ivlev.wh.model.DicStores;
import aleksey.ivlev.wh.model.InStock;
import aleksey.ivlev.wh.model.Product;

public interface InStockManager {

	void addInstock(InStock instock);

	InStock getInstock(Product product, DicStores dicStores);

	void editInstock(InStock instock);

	Long getProductCount(String prodName, String storName);
	
	void createOrUpdateInStock(Product product, DicStores dicStores, Long incdCount);
	
	void updateInStock(Product product, DicStores dicStores, Long outdCount);
}
