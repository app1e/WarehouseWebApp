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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "OUTCOME_DETAILS")
public class OutcomeDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2819908138793086301L;

	private Long outdId;
	private Long outdCount;
	private Long outdPrice;
	private Outcomes outcomes;
	private Product product;

	

	public OutcomeDetails() {
	}

	public OutcomeDetails(Outcomes outcomes, Product product, Long outdCount,
			Long outdPrice) {
		this.outcomes = outcomes;
		this.product = product;
		this.outdCount = outdCount;
		this.outdPrice = outdPrice;
	}

	@Id
	@SequenceGenerator(name="outDetIdSeq", sequenceName="SEQ_OUTCOME_DETAILS", allocationSize=10, initialValue=1)
    @GeneratedValue(generator="outDetIdSeq")
	@Column(name = "OUTD_ID")
	public Long getOutdId() {
		return outdId;
	}

	public void setOutdId(Long outdId) {
		this.outdId = outdId;
	}
	
	@Column(name = "OUTD_COUNT")
	public Long getOutdCount() {
		return outdCount;
	}

	public void setOutdCount(Long outdCount) {
		this.outdCount = outdCount;
	}
	
	@Column(name = "OUTD_PRICE")
	public Long getOutdPrice() {
		return outdPrice;
	}

	public void setOutdPrice(Long outdPrice) {
		this.outdPrice = outdPrice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OUTD_OUT_ID", nullable = false)
	public Outcomes getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(Outcomes outcomes) {
		this.outcomes = outcomes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OUTD_PROD_ID", nullable = false)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof OutcomeDetails))
			return false;
		OutcomeDetails castOther = (OutcomeDetails) other;
		return new EqualsBuilder().append(outdId, castOther.outdId)
				.append(outdCount, castOther.outdCount)
				.append(outdPrice, castOther.outdPrice)
				.append(outcomes, castOther.outcomes)
				.append(product, castOther.product).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(outdId).append(outdCount)
				.append(outdPrice).append(outcomes).append(product)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("outdId", outdId)
				.append("outdCount", outdCount).append("outdPrice", outdPrice)
				.append("outcomes", outcomes).append("product", product)
				.toString();
	}

	
	
}
