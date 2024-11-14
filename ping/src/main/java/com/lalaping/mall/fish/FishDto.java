package com.lalaping.mall.fish;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lalaping.infra.code.CodeDto;

public class FishDto {
	private String fsSeq;
	private String fsName;
	private Integer fsSeason;
	private Integer fsDelNy;
	private Date fsRegDate;
	
	private String ship_spSeq;
	private String fsNameList;
	
	//for cache
	public static List<FishDto> cachedFishArrayList = new ArrayList<FishDto>();
	
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
	public String getShip_spSeq() {
		return ship_spSeq;
	}
	public void setShip_spSeq(String ship_spSeq) {
		this.ship_spSeq = ship_spSeq;
	}
	public String getFsNameList() {
		return fsNameList;
	}
	public void setFsNameList(String fsNameList) {
		this.fsNameList = fsNameList;
	}

}
