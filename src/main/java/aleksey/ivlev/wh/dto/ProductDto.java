package aleksey.ivlev.wh.dto;

import java.util.Date;

public class ProductDto {
	
	private Date incDate;
	private String incSupplierName;
	private String storName;
	private String prodName;
	private Long incdCount;
	private String prodDescription;
	private Long incdPrice;
	private Date outDate;
	private String outCustomer;
	private Long outdCount;
	public Date getIncDate() {
		return incDate;
	}
	public void setIncDate(Date incDate) {
		this.incDate = incDate;
	}
	public String getIncSupplierName() {
		return incSupplierName;
	}
	public void setIncSupplierName(String incSupplierName) {
		this.incSupplierName = incSupplierName;
	}
	public String getStorName() {
		return storName;
	}
	public void setStorName(String storName) {
		this.storName = storName;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Long getIncdCount() {
		return incdCount;
	}
	public void setIncdCount(Long incdCount) {
		this.incdCount = incdCount;
	}
	public String getProdDescription() {
		return prodDescription;
	}
	public void setProdDescription(String prodDescription) {
		this.prodDescription = prodDescription;
	}
	public Long getIncdPrice() {
		return incdPrice;
	}
	public void setIncdPrice(Long incdPrice) {
		this.incdPrice = incdPrice;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public String getOutCustomer() {
		return outCustomer;
	}
	public void setOutCustomer(String outCustomer) {
		this.outCustomer = outCustomer;
	}
	public Long getOutdCount() {
		return outdCount;
	}
	public void setOutdCount(Long outdCount) {
		this.outdCount = outdCount;
	}
	
	
	
}
