package aleksey.ivlev.wh.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "OUTCOMES")
public class Outcomes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4123748089496840718L;
	
	private Long outId;
	private Date outDate;
	private String outCustomer;
	Set<OutcomeDetails> outcomeDetails = new HashSet<OutcomeDetails>(0);
    private DicStores dicStores;

	public Outcomes() {
	}

	public Outcomes(Date outDate, String outCustomer, DicStores dicStores) {
		this.outDate = outDate;
		this.outCustomer = outCustomer;
		this.dicStores = dicStores;
	}

	public Outcomes(DicStores dicStores) {
		this.dicStores = dicStores;
	}

	@Id
	@SequenceGenerator(name="outIdSeq", sequenceName="SEQ_OUTCOMES", allocationSize=10, initialValue=1)
    @GeneratedValue(generator="outIdSeq")
	@Column(name = "OUT_ID")
	public Long getOutId() {
		return outId;
	}

	public void setOutId(Long outId) {
		this.outId = outId;
	}

	@Column(name = "OUT_DATE")
	@Temporal(TemporalType.DATE)
	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	
	@Column(name = "OUT_CUSTOMER")
	public String getOutCustomer() {
		return outCustomer;
	}

	public void setOutCustomer(String outCustomer) {
		this.outCustomer = outCustomer;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "outcomes")
	public Set<OutcomeDetails> getOutcomeDetails() {
		return outcomeDetails;
	}

	public void setOutcomeDetails(Set<OutcomeDetails> outcomeDetails) {
		this.outcomeDetails = outcomeDetails;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OUT_STOR_ID", nullable = false)
	public DicStores getDicStores() {
		return dicStores;
	}

	public void setDicStores(DicStores dicStores) {
		this.dicStores = dicStores;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Outcomes))
			return false;
		Outcomes castOther = (Outcomes) other;
		return new EqualsBuilder().append(outId, castOther.outId)
				.append(outDate, castOther.outDate)
				.append(outCustomer, castOther.outCustomer)
				.append(outcomeDetails, castOther.outcomeDetails).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(outId).append(outDate)
				.append(outCustomer).append(outcomeDetails).toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("outId", outId)
				.append("outDate", outDate).append("outCustomer", outCustomer)
				.append("outcomeDetails", outcomeDetails).toString();
	}
	
}
