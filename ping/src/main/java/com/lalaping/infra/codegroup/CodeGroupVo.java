package com.lalaping.infra.codegroup;

import java.util.Date;

import com.lalaping.common.base.BaseVo;

public class CodeGroupVo extends BaseVo{
	private String cgSeq;
	private Date cgRegDate;
	private Date cgModDate;
//-------------
	public String getCgSeq() {
		return cgSeq;
	}

	public void setCgSeq(String cgSeq) {
		this.cgSeq = cgSeq;
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
    
}