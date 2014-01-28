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
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="INCOMES")
public class Incomes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5436525237549579852L;
	
    private Long incId;
    private Date incDate;
    private String incSupplierName;
    Set<IncomeDetails> incomeDetails = new HashSet<IncomeDetails>(0);
    private DicStores dicStores;

    
    
    public Incomes(){
    }
    
    public Incomes(DicStores dicStores) {
    	this.dicStores = dicStores;
    }
    
    public Incomes(Date incDate, String incSupplierName, DicStores dicStores) {
        this.incDate = incDate;
        this.incSupplierName = incSupplierName;
        this.dicStores = dicStores;
    }
    
    @Id
	@SequenceGenerator(name="incIdSeq", sequenceName="SEQ_INCOMES", allocationSize=10, initialValue=1)
    @GeneratedValue(generator="incIdSeq")
    @Column(name = "INC_ID")
    public Long getIncId() {
        return incId;
    }

    public void setIncId(Long incId) {
        this.incId = incId;
    }
    
    @Column(name = "INC_DATE")
    @Temporal(TemporalType.DATE)
    public Date getIncDate() {
        return incDate;
    }
    
    public void setIncDate(Date incDate) {
        this.incDate = incDate;
    }
    @NotEmpty
    @Column(name = "INC_SUPPLIER_NAME")
    public String getIncSupplierName() {
        return incSupplierName;
    }

    public void setIncSupplierName(String incSupplierName) {
        this.incSupplierName = incSupplierName;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "INC_STOR_ID", nullable = false)
	public DicStores getDicStores() {
		return dicStores;
	}


	public void setDicStores(DicStores dicStores) {
		this.dicStores = dicStores;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "incomes")
    public Set<IncomeDetails> getIncomeDetails() {
		return incomeDetails;
	}

	public void setIncomeDetails(Set<IncomeDetails> incomeDetails) {
		this.incomeDetails = incomeDetails;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Incomes))
			return false;
		Incomes castOther = (Incomes) other;
		return new EqualsBuilder().append(incId, castOther.incId)
				.append(incDate, castOther.incDate)
				.append(incSupplierName, castOther.incSupplierName)
				.append(incomeDetails, castOther.incomeDetails).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(incId).append(incDate)
				.append(incSupplierName).append(incomeDetails).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("incId", incId)
				.append("incDate", incDate)
				.append("incSupplierName", incSupplierName)
				.append("incomeDetails", incomeDetails).toString();
	}
    
	
}
