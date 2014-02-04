package aleksey.ivlev.wh.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="PRODUCTS")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1252712188067347764L;
	
	
	private Long prodId;
	private String prodName;
	private String prodDescription;
	private Set<IncomeDetails> incomeDetails = new HashSet<IncomeDetails>(0);
	private Set<OutcomeDetails> outcomeDetails = new HashSet<OutcomeDetails>(0);
	private Set<InStock> inStock = new HashSet<InStock>(0);

	public Product(){
    }
	
	public Product(String prodName, String prodDescription) {
        this.prodName = prodName;
        this.prodDescription = prodDescription;
    }
	
	@Id
	@SequenceGenerator(name="prodIdSeq", sequenceName="SEQ_PRODUCT", allocationSize=10, initialValue=1)
    @GeneratedValue(generator="prodIdSeq")
	@Column(name = "PROD_ID")
	public Long getProdId() {
		return prodId;
	}
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}
	
	@NotEmpty
	@Column(name = "PROD_NAME")
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	@Column(name = "PROD_DESCRIPTION")
	public String getProdDescription() {
		return prodDescription;
	}
	public void setProdDescription(String prodDescription) {
		this.prodDescription = prodDescription;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<IncomeDetails> getIncomeDetails() {
		return incomeDetails;
	}
	public void setIncomeDetails(Set<IncomeDetails> incomeDetails) {
		this.incomeDetails = incomeDetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<OutcomeDetails> getOutcomeDetails() {
		return outcomeDetails;
	}

	public void setOutcomeDetails(Set<OutcomeDetails> outcomeDetails) {
		this.outcomeDetails = outcomeDetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<InStock> getInStock() {
		return inStock;
	}

	public void setInStock(Set<InStock> inStock) {
		this.inStock = inStock;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Product))
			return false;
		Product castOther = (Product) other;
		return new EqualsBuilder().append(prodId, castOther.prodId)
				.append(prodName, castOther.prodName)
				.append(prodDescription, castOther.prodDescription)
				.append(incomeDetails, castOther.incomeDetails)
				.append(outcomeDetails, castOther.outcomeDetails)
				.append(inStock, castOther.inStock).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(prodId).append(prodName)
				.append(prodDescription).append(incomeDetails)
				.append(outcomeDetails).append(inStock).toHashCode();
	}
	

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("prodId", prodId)
				.append("prodName", prodName)
				.append("prodDescription", prodDescription)
				.append("incomeDetails", incomeDetails)
				.append("outcomeDetails", outcomeDetails)
				.append("inStock", inStock)
				.toString();
	}	
}