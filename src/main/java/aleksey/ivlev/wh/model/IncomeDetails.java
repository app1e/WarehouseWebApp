package aleksey.ivlev.wh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INCOME_DETAILS")
public class IncomeDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4519812809997089577L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "INCD_ID")
    private int incdId;

    @Column(name = "INCD_PROD_ID")
    private int incdProdId;

    @Column(name = "INCD_PRICE")
    private Long price;

    @Column(name = "INCD_COUNT")
    private int incdCount;

    @Column(name = "INCD_INC_ID")
    private int incdIncId;
    
    public IncomeDetails(){
    }
    
    public IncomeDetails(int incdProdId, Long price, int incdCount, int incdIncId) {
        this.incdProdId = incdProdId;
        this.price = price;
        this.incdCount = incdCount;
        this.incdIncId = incdIncId;
    }

    public int getIncdCount() {
        return incdCount;
    }

    public void setIncdCount(int incdCount) {
        this.incdCount = incdCount;
    }

    public int getIncdId() {
        return incdId;
    }

    public void setIncdId(int incdId) {
        this.incdId = incdId;
    }

    public int getIncdIncId() {
        return incdIncId;
    }

    public void setIncdIncId(int incdIncId) {
        this.incdIncId = incdIncId;
    }

    public int getIncdProdId() {
        return incdProdId;
    }

    public void setIncdProdId(int incdProdId) {
        this.incdProdId = incdProdId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + incdId;
		result = prime * result + incdProdId;
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
		IncomeDetails other = (IncomeDetails) obj;
		if (incdId != other.incdId)
			return false;
		if (incdProdId != other.incdProdId)
			return false;
		return true;
	}

    

}
