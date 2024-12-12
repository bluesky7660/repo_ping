package com.lalaping.mall.review;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lalaping.common.base.BaseVo;

public class ReviewDto extends BaseVo {
	private String mrSeq;
	private String mrComment;
	private Integer mrDelNy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mrRegDate;
	private String mapPoint_mpSeq;
	private String member_mmSeq;
	private String mpTitle;
	private String mmName;
	
//	------------------------------------
	
	public String getMrSeq() {
		return mrSeq;
	}
	public void setMrSeq(String mrSeq) {
		this.mrSeq = mrSeq;
	}
	public String getMrComment() {
		return mrComment;
	}
	public void setMrComment(String mrComment) {
		this.mrComment = mrComment;
	}
	public Integer getMrDelNy() {
		return mrDelNy;
	}
	public void setMrDelNy(Integer mrDelNy) {
		this.mrDelNy = mrDelNy;
	}
	public Date getMrRegDate() {
		return mrRegDate;
	}
	public void setMrRegDate(Date mrRegDate) {
		this.mrRegDate = mrRegDate;
	}
	
	public String getMember_mmSeq() {
		return member_mmSeq;
	}
	public void setMember_mmSeq(String member_mmSeq) {
		this.member_mmSeq = member_mmSeq;
	}
	public String getMpTitle() {
		return mpTitle;
	}
	public void setMpTitle(String mpTitle) {
		this.mpTitle = mpTitle;
	}
	public String getMmName() {
		return mmName;
	}
	public void setMmName(String mmName) {
		this.mmName = mmName;
	}
	public String getMapPoint_mpSeq() {
		return mapPoint_mpSeq;
	}
	public void setMapPoint_mpSeq(String mapPoint_mpSeq) {
		this.mapPoint_mpSeq = mapPoint_mpSeq;
	}
	
	
	
	

	
	
	
	

}
