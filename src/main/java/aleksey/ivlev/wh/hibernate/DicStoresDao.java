package aleksey.ivlev.wh.hibernate;

import java.util.List;

import aleksey.ivlev.wh.model.DicStores;

public interface DicStoresDao {
	void addDicStores(DicStores dicStores);

	DicStores getDicStore(String storName);

	List<DicStores> getAllDicStores();

	List<DicStores> getIdAllDicStores();

	List<DicStores> getReports();

	Long getPrice(String prodName, String storName);
	
	
}
