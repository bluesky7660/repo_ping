package com.lalaping.mall.order;

import com.lalaping.common.base.BaseVo;

public class OrderVo extends BaseVo {
	private String member_mmSeq;
	private String shOdType;
	private Integer shOdCount;
//	-------------------------------

	public String getMember_mmSeq() {
		return member_mmSeq;
	}

	public void setMember_mmSeq(String member_mmSeq) {
		this.member_mmSeq = member_mmSeq;
	}

	public String getShOdType() {
		return shOdType;
	}

	public void setShOdType(String shOdType) {
		this.shOdType = shOdType;
	}

	public Integer getShOdCount() {
		return shOdCount;
	}

	public void setShOdCount(Integer shOdCount) {
		this.shOdCount = shOdCount;
	}
	

}
