package com.lalaping.mall.fish;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FishDto {
	private String fsSeq;
	private String fsName;
	private Integer fsSeason;
	private Boolean fsDelNy;
	private Date fsRegDate;
	
	private String ship_spSeq;
	private String fsNameList;
	
	private String season_ssSeq;
	private String fish_fsSeq;
//	private List<> seasons;
	
	//for cache
	public static List<FishDto> cachedFishArrayList = new ArrayList<FishDto>();
	
//	------------------------------ 
	public String getFsSeq() {
		return fsSeq;
	}
	public String getFish_fsSeq() {
		return fish_fsSeq;
	}
	public void setFish_fsSeq(String fish_fsSeq) {
		this.fish_fsSeq = fish_fsSeq;
	}
	public String getSeason_ssSeq() {
		return season_ssSeq;
	}
	public void setSeason_ssSeq(String season_ssSeq) {
		this.season_ssSeq = season_ssSeq;
	}
	public static List<FishDto> getCachedFishArrayList() {
		return cachedFishArrayList;
	}
	public static void setCachedFishArrayList(List<FishDto> cachedFishArrayList) {
		FishDto.cachedFishArrayList = cachedFishArrayList;
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
	public Boolean getFsDelNy() {
		return fsDelNy;
	}
	public void setFsDelNy(Boolean fsDelNy) {
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
