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
@Table(name = "OUTCOMES")
public class Outcomes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4123748089496840718L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OUT_ID")
	private int outId;

	@Column(name = "OUT_DATE")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date outDate;

	@Column(name = "OUT_CUSTOMER")
	private String outCustomer;

	@Column(name = "OUT_STOR_ID")
	private int outStorId;

	public Outcomes() {
	}

	public Outcomes(Date outDate, String outCustomer, int outStorId) {
		this.outDate = outDate;
		this.outCustomer = outCustomer;
		this.outStorId = outStorId;
	}

	public Outcomes(int outStorId) {
		this.outStorId = outStorId;
	}

	public String getOutCustomer() {
		return outCustomer;
	}

	public void setOutCustomer(String outCustomer) {
		this.outCustomer = outCustomer;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public int getOutId() {
		return outId;
	}

	public void setOutId(int outId) {
		this.outId = outId;
	}

	public int getOutStorId() {
		return outStorId;
	}

	public void setOutStorId(int outStorId) {
		this.outStorId = outStorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((outCustomer == null) ? 0 : outCustomer.hashCode());
		result = prime * result + outId;
		result = prime * result + outStorId;
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
		Outcomes other = (Outcomes) obj;
		if (outCustomer == null) {
			if (other.outCustomer != null)
				return false;
		} else if (!outCustomer.equals(other.outCustomer))
			return false;
		if (outId != other.outId)
			return false;
		if (outStorId != other.outStorId)
			return false;
		return true;
	}
	
	

}
