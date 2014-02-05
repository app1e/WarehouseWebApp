package aleksey.ivlev.wh.managers;

import java.util.List;

import aleksey.ivlev.wh.model.DicStores;
import aleksey.ivlev.wh.model.InStock;
import aleksey.ivlev.wh.model.Product;

public interface InStockManager {

	void addInstock(InStock instock);

	 List<InStock> getInstock(Product product, DicStores dicStores);

	void editInstock(InStock instock);

	Long getProductCount(String prodName, String storName);
}
