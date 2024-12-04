package com.lalaping.mall.port;

import java.util.Date;

public class PortDto {
	private String ptSeq;
	private Integer ptArea;
	private String ptName;
	private String ptDesc;
	private Double ptLongitude;
	private Double ptLatitude;
	private String ptAddress;
	private Integer ptDelNy;
	private Date ptRegDate;
	
//	-------------------------
	
	public String getPtSeq() {
		return ptSeq;
	}
	public void setPtSeq(String ptSeq) {
		this.ptSeq = ptSeq;
	}
	public Integer getPtArea() {
		return ptArea;
	}
	public void setPtArea(Integer ptArea) {
		this.ptArea = ptArea;
	}
	public String getPtName() {
		return ptName;
	}
	public void setPtName(String ptName) {
		this.ptName = ptName;
	}
	public String getPtDesc() {
		return ptDesc;
	}
	public void setPtDesc(String ptDesc) {
		this.ptDesc = ptDesc;
	}
	public String getPtAddress() {
		return ptAddress;
	}
	public void setPtAddress(String ptAddress) {
		this.ptAddress = ptAddress;
	}
	public Integer getPtDelNy() {
		return ptDelNy;
	}
	public void setPtDelNy(Integer ptDelNy) {
		this.ptDelNy = ptDelNy;
	}
	public Date getPtRegDate() {
		return ptRegDate;
	}
	public void setPtRegDate(Date ptRegDate) {
		this.ptRegDate = ptRegDate;
	}
	public Double getPtLongitude() {
		return ptLongitude;
	}
	public void setPtLongitude(Double ptLongitude) {
		this.ptLongitude = ptLongitude;
	}
	public Double getPtLatitude() {
		return ptLatitude;
	}
	public void setPtLatitude(Double ptLatitude) {
		this.ptLatitude = ptLatitude;
	}


}
