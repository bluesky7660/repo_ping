package com.lalaping.mall.ship;

import com.lalaping.common.base.BaseVo;

public class ShipVo extends BaseVo {
	private Integer shEatNy;
	private Integer shRentalNy;
	private String shArea;
	private String shName;
	private String shport;
	private Integer shRemain;
	private String shfish;
	private Integer shmin_price;
	private Integer shmax_price;
	private String baseSpSeq;
	private String spSeq;
	private String port_ptSeq;
	
	private String ptSeq;
	private String ptName;
	private String ptArea;
	
	//	-----------------------------
	
	public Integer getShEatNy() {
		return shEatNy;
	}
	public String getPtArea() {
		return ptArea;
	}
	public void setPtArea(String ptArea) {
		this.ptArea = ptArea;
	}
	public String getPtSeq() {
		return ptSeq;
	}
	public void setPtSeq(String ptSeq) {
		this.ptSeq = ptSeq;
	}
	public String getPtName() {
		return ptName;
	}
	public void setPtName(String ptName) {
		this.ptName = ptName;
	}
	public void setShEatNy(Integer shEatNy) {
		this.shEatNy = shEatNy;
	}
	public Integer getShRentalNy() {
		return shRentalNy;
	}
	public void setShRentalNy(Integer shRentalNy) {
		this.shRentalNy = shRentalNy;
	}
	public String getShArea() {
		return shArea;
	}
	public void setShArea(String shArea) {
		this.shArea = shArea;
	}
	public String getPort_ptSeq() {
		return port_ptSeq;
	}
	public void setPort_ptSeq(String port_ptSeq) {
		this.port_ptSeq = port_ptSeq;
	}
	public String getShName() {
		return shName;
	}
	public void setShName(String shName) {
		this.shName = shName;
	}
	public String getShport() {
		return shport;
	}
	public void setShport(String shport) {
		this.shport = shport;
	}
	public Integer getShRemain() {
		return shRemain;
	}
	public void setShRemain(Integer shRemain) {
		this.shRemain = shRemain;
	}
	public String getShfish() {
		return shfish;
	}
	public void setShfish(String shfish) {
		this.shfish = shfish;
	}
	public Integer getShmin_price() {
		return shmin_price;
	}
	public void setShmin_price(Integer shmin_price) {
		if(shmin_price == null) {
			this.shmin_price = 1;
		}else {
			this.shmin_price = shmin_price;
		}
	}
	public Integer getShmax_price() {
		return shmax_price;
	}
	public void setShmax_price(Integer shmax_price) {
		if(shmax_price == null) {
			this.shmax_price = 500000;
		}else {
			this.shmax_price = shmax_price;
		}
	}
	public String getBaseSpSeq() {
		return baseSpSeq;
	}
	public void setBaseSpSeq(String baseSpSeq) {
		this.baseSpSeq = baseSpSeq;
	}
	public String getSpSeq() {
		return spSeq;
	}
	public void setSpSeq(String spSeq) {
		this.spSeq = spSeq;
	}
	
	

}
