package aleksey.ivlev.wh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="PRODUCT")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1252712188067347764L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PROD_ID")
	private int prodId;
	
	@Column(name = "PROD_NAME")
	private String prodName;
	
	@Column(name = "PROD_DESCRIPTION")
	private String prodDescription;
	
	public Product(){
    }
	
	public Product(String prodName, String prodDescription) {
        this.prodName = prodName;
        this.prodDescription = prodDescription;
    }
	
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdDescription() {
		return prodDescription;
	}
	public void setProdDescription(String prodDescription) {
		this.prodDescription = prodDescription;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + prodId;
		result = prime * result
				+ ((prodName == null) ? 0 : prodName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (prodId != other.prodId)
			return false;
		if (prodName == null) {
			if (other.prodName != null)
				return false;
		} else if (!prodName.equals(other.prodName))
			return false;
		return true;
	}
	
	
	
	
}
