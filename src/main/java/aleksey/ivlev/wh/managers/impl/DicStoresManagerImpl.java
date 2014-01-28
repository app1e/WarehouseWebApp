package aleksey.ivlev.wh.managers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aleksey.ivlev.wh.hibernate.DicStoresDao;
import aleksey.ivlev.wh.managers.DicStoresManager;
import aleksey.ivlev.wh.model.DicStores;

@Service
public class DicStoresManagerImpl implements DicStoresManager {

	@Autowired
	private DicStoresDao dicStoresDao;
	
	public void setDicStoresDao(DicStoresDao dicStoresDao) {
		this.dicStoresDao = dicStoresDao;
	}

	@Override
	public void addDicStores(DicStores dicStores) {
		dicStoresDao.addDicStores(dicStores);
	}

	@Override
	public DicStores getDicStore(String storName) {
		return dicStoresDao.getDicStore(storName);
	}

	@Override
	public List<DicStores> getAllDicStores() {
		return dicStoresDao.getAllDicStores();
	}

	@Override
	public List<DicStores> getIdAllDicStores() {
		return dicStoresDao.getIdAllDicStores();
	}

	@Override
	public List<DicStores> getReports() {
		return dicStoresDao.getReports();
	}

	@Override
	public Long getPrice(String prodName, String storName) {
		return dicStoresDao.getPrice(prodName, storName);
	}

}
