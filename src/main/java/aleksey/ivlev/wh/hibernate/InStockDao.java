package aleksey.ivlev.wh.hibernate;

import aleksey.ivlev.wh.model.DicStores;
import aleksey.ivlev.wh.model.InStock;
import aleksey.ivlev.wh.model.Product;

public interface InStockDao {
	
	void addInstock(InStock instock);

	InStock getInstock(Product product, DicStores dicStores);

    public void editInstock(InStock instock);

    Long getProductCount(String prodName, String storName);
}
