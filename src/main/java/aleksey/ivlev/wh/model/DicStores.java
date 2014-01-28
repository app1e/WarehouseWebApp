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

@Entity
@Table(name = "DIC_STORES")
public class DicStores implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6305029992643994762L;
	
	private Long storId;
	private String storName;
	Set<Incomes> incomes = new  HashSet<Incomes>(0);
	Set<Outcomes> outcomes = new HashSet<Outcomes>(0);
	Set<InStock> instock = new HashSet<InStock>(0);

	public DicStores() {
	}

	public DicStores(String storName) {
		this.storName = storName;
	}
	
	@Id
	@SequenceGenerator(name="dicStIdSeq", sequenceName="SEQ_DIC_STORES", allocationSize=10, initialValue=1)
    @GeneratedValue(generator="dicStIdSeq")
	@Column(name = "STOR_ID")
	public Long getStorId() {
		return storId;
	}

	public void setStorId(Long storId) {
		this.storId = storId;
	}
	
	@Column(name = "STOR_NAME")
	public String getStorName() {
		return storName;
	}

	public void setStorName(String storName) {
		this.storName = storName;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dicStores")
	public Set<Incomes> getIncomes() {
		return incomes;
	}
	
	public void setIncomes(Set<Incomes> incomes) {
		this.incomes = incomes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dicStores")
	public Set<Outcomes> getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(Set<Outcomes> outcomes) {
		this.outcomes = outcomes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dicStores")
	public Set<InStock> getInstock() {
		return instock;
	}

	public void setInstock(Set<InStock> instock) {
		this.instock = instock;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof DicStores))
			return false;
		DicStores castOther = (DicStores) other;
		return new EqualsBuilder().append(storId, castOther.storId)
				.append(storName, castOther.storName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(storId).append(storName).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("storId", storId)
				.append("storName", storName).append("incomes", incomes)
				.append("outcomes", outcomes).append("instock", instock)
				.toString();
	}

	
	
}
