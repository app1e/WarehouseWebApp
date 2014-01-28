package aleksey.ivlev.wh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "DIC_STORES")
@NamedQueries({@NamedQuery(name="DicStores.getDSId", query="select d from DicStores d where LOWER(d.storName) LIKE :name"),
    @NamedQuery(name="DicStores.getDicStores", query="select d from DicStores d")})
public class DicStores implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6305029992643994762L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STOR_ID")
	private int storId;

	@Column(name = "STOR_NAME")
	private String storName;

	public DicStores() {
	}

	public DicStores(String storName) {
		this.storName = storName;
	}

	public int getStorId() {
		return storId;
	}

	public void setStorId(int storId) {
		this.storId = storId;
	}

	public String getStorName() {
		return storName;
	}

	public void setStorName(String storName) {
		this.storName = storName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + storId;
		result = prime * result
				+ ((storName == null) ? 0 : storName.hashCode());
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
		DicStores other = (DicStores) obj;
		if (storId != other.storId)
			return false;
		if (storName == null) {
			if (other.storName != null)
				return false;
		} else if (!storName.equals(other.storName))
			return false;
		return true;
	}
	
	

}
