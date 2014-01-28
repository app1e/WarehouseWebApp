package aleksey.ivlev.wh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "INSTOCK")
public class InStock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7115926214639147930L;

	
	private Long stockId;
	private Long storCount;
	private Product product;
	private DicStores dicStores;
	
	public InStock() {
	}

	public InStock(Product product, DicStores dicStores, Long storCount) {
		this.product = product;
		this.dicStores = dicStores;
		this.storCount = storCount;
	}
	
	@Id
	@SequenceGenerator(name="inStockIdSeq", sequenceName="SEQ_INSTOCK", allocationSize=10, initialValue=1)
    @GeneratedValue(generator="inStockIdSeq")
	@Column(name = "STOCK_ID")
	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	
	@Column(name = "STOR_COUNT")
	public Long getStorCount() {
		return storCount;
	}

	public void setStorCount(Long storCount) {
		this.storCount = storCount;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STOCK_PROD_ID", nullable = false)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STOCK_STOR_ID", nullable = false)
	public DicStores getDicStores() {
		return dicStores;
	}

	public void setDicStores(DicStores dicStores) {
		this.dicStores = dicStores;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof InStock))
			return false;
		InStock castOther = (InStock) other;
		return new EqualsBuilder().append(stockId, castOther.stockId)
				.append(storCount, castOther.storCount)
				.append(product, castOther.product)
				.append(dicStores, castOther.dicStores).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(stockId).append(storCount)
				.append(product).append(dicStores).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("stockId", stockId)
				.append("storCount", storCount).append("product", product)
				.append("dicStores", dicStores).toString();
	}

	
	
}
