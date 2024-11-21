package com.lalaping.mall.order;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OrderDto {
	private String spSeq;
	private String spName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date spStart;
	private Integer spEatNy;
	private Integer spRentalNy;
	private Integer spPrice;
	private Integer spTotal;
	private String ptName;
	private String ptAddress;
	private String mmTel;
	
//	---------------------------------
	
	public String getSpSeq() {
		return spSeq;
	}
	public void setSpSeq(String spSeq) {
		this.spSeq = spSeq;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public Date getSpStart() {
		return spStart;
	}
	public void setSpStart(Date spStart) {
		this.spStart = spStart;
	}
	public Integer getSpEatNy() {
		return spEatNy;
	}
	public void setSpEatNy(Integer spEatNy) {
		this.spEatNy = spEatNy;
	}
	public Integer getSpRentalNy() {
		return spRentalNy;
	}
	public void setSpRentalNy(Integer spRentalNy) {
		this.spRentalNy = spRentalNy;
	}
	public Integer getSpPrice() {
		return spPrice;
	}
	public void setSpPrice(Integer spPrice) {
		this.spPrice = spPrice;
	}
	public Integer getSpTotal() {
		return spTotal;
	}
	public void setSpTotal(Integer spTotal) {
		this.spTotal = spTotal;
	}
	public String getPtName() {
		return ptName;
	}
	public void setPtName(String ptName) {
		this.ptName = ptName;
	}
	public String getPtAddress() {
		return ptAddress;
	}
	public void setPtAddress(String ptAddress) {
		this.ptAddress = ptAddress;
	}
	public String getMmTel() {
		return mmTel;
	}
	public void setMmTel(String mmTel) {
		this.mmTel = mmTel;
	}
	
	
	
	

	
	

	
	
	
	

}
