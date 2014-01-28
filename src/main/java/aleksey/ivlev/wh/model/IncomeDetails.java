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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "INCOME_DETAILS")
public class IncomeDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4519812809997089577L;

	
	private Long incdId;
	private Product product;
	private Long incdPrice;
	private Long incdCount;
	private Incomes incomes;

	public IncomeDetails() {
	}

	public IncomeDetails(Product product, Long incdPrice, Long incdCount,
			Incomes incomes){
		this.product = product;
		this.incdPrice = incdPrice;
		this.incdCount = incdCount;
		this.incomes = incomes;
	}
	
	@Id
	@SequenceGenerator(name="incDetIdSeq", sequenceName="SEQ_INCOME_DETAILS", allocationSize=10, initialValue=1)
    @GeneratedValue(generator="incDetIdSeq")
	@Column(name = "INCD_ID")
	public Long getIncdId() {
		return incdId;
	}

	public void setIncdId(Long incdId) {
		this.incdId = incdId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INCD_PROD_ID", nullable = false)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Column(name = "INCD_PRICE")
	public Long getIncdPrice() {
		return incdPrice;
	}

	public void setIncdPrice(Long incdPrice) {
		this.incdPrice = incdPrice;
	}
	
	@Column(name = "INCD_COUNT")
	public Long getIncdCount() {
		return incdCount;
	}

	public void setIncdCount(Long incdCount) {
		this.incdCount = incdCount;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INCD_INC_ID", nullable = false)
	public Incomes getIncomes() {
		return incomes;
	}

	public void setIncomes(Incomes incomes) {
		this.incomes = incomes;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof IncomeDetails))
			return false;
		IncomeDetails castOther = (IncomeDetails) other;
		return new EqualsBuilder().append(incdId, castOther.incdId)
				.append(product, castOther.product)
				.append(incdPrice, castOther.incdPrice)
				.append(incdCount, castOther.incdCount)
				.append(incomes, castOther.incomes).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(incdId).append(product)
				.append(incdPrice).append(incdCount).append(incomes)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("incdId", incdId)
				.append("product", product).append("incdPrice", incdPrice)
				.append("incdCount", incdCount).append("incomes", incomes)
				.toString();
	}
	
	
}
