package aleksey.ivlev.wh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OUTCOME_DETAILS")
public class OutcomeDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2819908138793086301L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "OUTD_ID")
	private int outdId;

	@Column(name = "OUTD_OUT_ID")
	private int outdOutId;

	@Column(name = "OUTD_PROD_ID")
	private int outdProdId;

	@Column(name = "OUTD_COUNT")
	private int outdCount;

	@Column(name = "OUTD_PRICE")
	private Long outdPrice;

	public OutcomeDetails() {
	}

	public OutcomeDetails(int outdOutId, int outdProdId, int outdCount,
			Long outdPrice) {
		this.outdOutId = outdOutId;
		this.outdProdId = outdProdId;
		this.outdCount = outdCount;
		this.outdPrice = outdPrice;
	}

	public int getOutdCount() {
		return outdCount;
	}

	public void setOutdCount(int outdCount) {
		this.outdCount = outdCount;
	}

	public int getOutdId() {
		return outdId;
	}

	public void setOutdId(int outdId) {
		this.outdId = outdId;
	}

	public int getOutdOutId() {
		return outdOutId;
	}

	public void setOutdOutId(int outdOutId) {
		this.outdOutId = outdOutId;
	}

	public Long getOutdPrice() {
		return outdPrice;
	}

	public void setOutdPrice(Long outdPrice) {
		this.outdPrice = outdPrice;
	}

	public int getOutdProdId() {
		return outdProdId;
	}

	public void setOutdProdId(int outdProdId) {
		this.outdProdId = outdProdId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + outdId;
		result = prime * result + outdOutId;
		result = prime * result + outdProdId;
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
		OutcomeDetails other = (OutcomeDetails) obj;
		if (outdId != other.outdId)
			return false;
		if (outdOutId != other.outdOutId)
			return false;
		if (outdProdId != other.outdProdId)
			return false;
		return true;
	}
	
	

}
