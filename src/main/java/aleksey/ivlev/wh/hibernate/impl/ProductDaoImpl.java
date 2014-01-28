package aleksey.ivlev.wh.hibernate.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import aleksey.ivlev.wh.hibernate.ProductDao;
import aleksey.ivlev.wh.model.Product;

public class ProductDaoImpl implements ProductDao{

	@PersistenceContext
    private EntityManager em;

    @Override
    public void addProduct(Product product) {
        em.persist(product);
    }

    @Override
    public int getProduct(String prodName) {
        List<Product> res = (List<Product>) em.createQuery(
                "select p from Product p where LOWER(p.prodName) LIKE :name", Product.class)
                .setParameter("name",  "%"+(prodName+"").toLowerCase()+"%")
                .getResultList();
        if(res == null || res.isEmpty()){
        return 0;
        } else
            return res.get(0).getProdId();
    }    

	@Override
	public List<Product> getProducts() {
		List<Product> resultList = (List<Product>) em.createQuery(
				"select p from Product", Product.class).getResultList();
		return resultList;
	}

}
