package aleksey.ivlev.wh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INSTOCK")
public class InStock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7115926214639147930L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STOCK_ID")
	private int stockId;

	@Column(name = "STOCK_PROD_ID")
	private int stockProdId;

	@Column(name = "STOCK_STOR_ID")
	private int stockStorId;

	@Column(name = "STOR_COUNT")
	private int storCount;

	public InStock() {
	}

	public InStock(int stockProdId, int stockStorId, int storCount) {
		this.stockProdId = stockProdId;
		this.stockStorId = stockStorId;
		this.storCount = storCount;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public int getStockProdId() {
		return stockProdId;
	}

	public void setStockProdId(int stockProdId) {
		this.stockProdId = stockProdId;
	}

	public int getStockStorId() {
		return stockStorId;
	}

	public void setStockStorId(int stockStorId) {
		this.stockStorId = stockStorId;
	}

	public int getStorCount() {
		return storCount;
	}

	public void setStorCount(int storCount) {
		this.storCount = storCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stockId;
		result = prime * result + stockProdId;
		result = prime * result + stockStorId;
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
		InStock other = (InStock) obj;
		if (stockId != other.stockId)
			return false;
		if (stockProdId != other.stockProdId)
			return false;
		if (stockStorId != other.stockStorId)
			return false;
		return true;
	}

}
