package com.lalaping.infra.codegroup;

import java.util.Date;

public class CodeGroupDto {
	private String cgSeq;
	private String cgName;
	private Integer cgOrder;
	private String  cgDesc;
	private Integer cgCodeCount;
	private Integer cgUseNy;
	private Integer cgDelNy;
	private Date cgRegDate;
	private Date cgModDate;
	private String cdName;
//----------------------------
	public String getCgSeq() {
		return cgSeq;
	}
	public void setCgSeq(String cgSeq) {
		this.cgSeq = cgSeq;
	}
	public String getCgName() {
		return cgName;
	}
	public void setCgName(String cgName) {
		this.cgName = cgName;
	}
	public Integer getCgOrder() {
		return cgOrder;
	}
	public void setCgOrder(Integer cgOrder) {
		this.cgOrder = cgOrder;
	}
	public String getCgDesc() {
		return cgDesc;
	}
	public void setCgDesc(String cgDesc) {
		this.cgDesc = cgDesc;
	}
	public Integer getCgCodeCount() {
		return cgCodeCount;
	}
	public void setCgCodeCount(Integer cgCodeCount) {
		this.cgCodeCount = cgCodeCount;
	}
	public Integer getCgUseNy() {
		return cgUseNy;
	}
	public void setCgUseNy(Integer cgUseNy) {
		this.cgUseNy = cgUseNy;
	}
	public Integer getCgDelNy() {
		return cgDelNy;
	}
	public void setCgDelNy(Integer cgDelNy) {
		this.cgDelNy = cgDelNy;
	}
	public Date getCgRegDate() {
		return cgRegDate;
	}
	public void setCgRegDate(Date cgRegDate) {
		this.cgRegDate = cgRegDate;
	}
	public Date getCgModDate() {
		return cgModDate;
	}
	public void setCgModDate(Date cgModDate) {
		this.cgModDate = cgModDate;
	}
	public String getCdName() {
		return cdName;
	}
	public void setCdName(String cdName) {
		this.cdName = cdName;
	}
	
	
}
