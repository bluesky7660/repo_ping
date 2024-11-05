package com.lalaping.infra.code;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lalaping.common.base.BaseVo;

public class CodeVo extends BaseVo {
	private String cdSeq;
	private String cdName;
	private Integer cdOrder;
	private String cdDesc;
	private Integer cdUseNy;
	private Integer cdDelNy;
	@DateTimeFormat
	private Date cdRegDate;
	@DateTimeFormat
	private Date cdModDate;
	private String codeGroup_cgSeq;
	private String cgSeq;
	private String cgName;
//-----------------------------
	public String getCdSeq() {
		return cdSeq;
	}
	public void setCdSeq(String cdSeq) {
		this.cdSeq = cdSeq;
	}
	public String getCdName() {
		return cdName;
	}
	public void setCdName(String cdName) {
		this.cdName = cdName;
	}
	public Integer getCdOrder() {
		return cdOrder;
	}
	public void setCdOrder(Integer cdOrder) {
		this.cdOrder = cdOrder;
	}
	public String getCdDesc() {
		return cdDesc;
	}
	public void setCdDesc(String cdDesc) {
		this.cdDesc = cdDesc;
	}
	public Integer getCdUseNy() {
		return cdUseNy;
	}
	public void setCdUseNy(Integer cdUseNy) {
		this.cdUseNy = cdUseNy;
	}
	public Integer getCdDelNy() {
		return cdDelNy;
	}
	public void setCdDelNy(Integer cdDelNy) {
		this.cdDelNy = cdDelNy;
	}
	public Date getCdRegDate() {
		return cdRegDate;
	}
	public void setCdRegDate(Date cdRegDate) {
		this.cdRegDate = cdRegDate;
	}
	public Date getCdModDate() {
		return cdModDate;
	}
	public void setCdModDate(Date cdModDate) {
		this.cdModDate = cdModDate;
	}
	public String getCodeGroup_cgSeq() {
		return codeGroup_cgSeq;
	}
	public void setCodeGroup_cgSeq(String codeGroup_cgSeq) {
		this.codeGroup_cgSeq = codeGroup_cgSeq;
	}
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
	
}
