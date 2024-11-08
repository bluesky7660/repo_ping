package com.lalaping.infra.fish;

import java.util.Date;

public class FishDto {
	private String fsSeq;
	private String fsName;
	private Integer fsSeason;
	private Integer fsDelNy;
	private Date fsRegDate;
	
//	------------------------------ 
	
	public String getFsSeq() {
		return fsSeq;
	}
	public void setFsSeq(String fsSeq) {
		this.fsSeq = fsSeq;
	}
	public String getFsName() {
		return fsName;
	}
	public void setFsName(String fsName) {
		this.fsName = fsName;
	}
	public Integer getFsSeason() {
		return fsSeason;
	}
	public void setFsSeason(Integer fsSeason) {
		this.fsSeason = fsSeason;
	}
	public Integer getFsDelNy() {
		return fsDelNy;
	}
	public void setFsDelNy(Integer fsDelNy) {
		this.fsDelNy = fsDelNy;
	}
	public Date getFsRegDate() {
		return fsRegDate;
	}
	public void setFsRegDate(Date fsRegDate) {
		this.fsRegDate = fsRegDate;
	}
	

	
	
	
	

}
