package com.lalaping.infra.member;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MemberDto {
	private String mmSeq;
    private String mmEmail;
    private String mmPasswd;
    private String mmName;
    private Integer mmGender;
    private String mmTel;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date mmBirthDay;
    private Boolean mmAdminNy;
    private Boolean mmDelNy;
    private Date mmRegDate;
    private Date mmModDate;
//------------------------------------
	public String getMmSeq() {
		return mmSeq;
	}
	public void setMmSeq(String mmSeq) {
		this.mmSeq = mmSeq;
	}
	public String getMmEmail() {
		return mmEmail;
	}
	public void setMmEmail(String mmEmail) {
		this.mmEmail = mmEmail;
	}
	public String getMmPasswd() {
		return mmPasswd;
	}
	public void setMmPasswd(String mmPasswd) {
		this.mmPasswd = mmPasswd;
	}
	public String getMmName() {
		return mmName;
	}
	public void setMmName(String mmName) {
		this.mmName = mmName;
	}
	public Integer getMmGender() {
		return mmGender;
	}
	public void setMmGender(Integer mmGender) {
		this.mmGender = mmGender;
	}
	public String getMmTel() {
		return mmTel;
	}
	public void setMmTel(String mmTel) {
		this.mmTel = mmTel;
	}
	public Date getMmBirthDay() {
		return mmBirthDay;
	}
	public void setMmBirthDay(Date mmBirthDay) {
		this.mmBirthDay = mmBirthDay;
	}
	public Boolean getMmAdminNy() {
		return mmAdminNy;
	}
	public void setMmAdminNy(Boolean mmAdminNy) {
		this.mmAdminNy = mmAdminNy;
	}
	public Boolean getMmDelNy() {
		return mmDelNy;
	}
	public void setMmDelNy(Boolean mmDelNy) {
		this.mmDelNy = mmDelNy;
	}
	public Date getMmRegDate() {
		return mmRegDate;
	}
	public void setMmRegDate(Date mmRegDate) {
		this.mmRegDate = mmRegDate;
	}
	public Date getMmModDate() {
		return mmModDate;
	}
	public void setMmModDate(Date mmModDate) {
		this.mmModDate = mmModDate;
	}
	
}
