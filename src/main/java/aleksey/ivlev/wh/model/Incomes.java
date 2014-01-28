package aleksey.ivlev.wh.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="INCOMES")
public class Incomes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5436525237549579852L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "INC_ID")
    private int incId;

    @Column(name = "INC_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date incDate;

    @Column(name = "INC_SUPPLIER_NAME")
    private String incSupplierName;

    @Column(name = "INC_STOR_ID")
    private int incStorId;

    public Date getIncDate() {
        return incDate;
    }
    
    public Incomes(){
    }
    
    public Incomes(Date incDate, String incSupplierName, int incStorId) {
        this.incDate = incDate;
        this.incSupplierName = incSupplierName;
        this.incStorId = incStorId;
    }

    
    public void setIncDate(Date incDate) {
        this.incDate = incDate;
    }

    public int getIncId() {
        return incId;
    }

    public void setIncId(int incId) {
        this.incId = incId;
    }

    public int getIncStorId() {
        return incStorId;
    }

    public void setIncStorId(int incStorId) {
        this.incStorId = incStorId;
    }

    public String getIncSupplierName() {
        return incSupplierName;
    }

    public void setIncSupplierName(String incSupplierName) {
        this.incSupplierName = incSupplierName;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + incId;
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
		Incomes other = (Incomes) obj;
		if (incId != other.incId)
			return false;
		return true;
	}
    
    
}
