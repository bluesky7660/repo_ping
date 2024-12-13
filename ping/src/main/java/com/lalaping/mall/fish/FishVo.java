package com.lalaping.mall.fish;

import com.lalaping.common.base.BaseVo;

public class FishVo extends BaseVo {
	private Integer shSeason;	
	private String spSeq;
	
	private String fsSeq;
	private String fsName;
	private String fsSeason;
	private Integer feDelNy;
	private String season_ssSeq;
	private String fish_fsSeq;

//	-----------------------------
	
	public Integer getShSeason() {
		return shSeason;
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

	public String getFsSeason() {
		return fsSeason;
	}

	public void setFsSeason(String fsSeason) {
		this.fsSeason = fsSeason;
	}

	public Integer getFeDelNy() {
		return feDelNy;
	}

	public void setFeDelNy(Integer feDelNy) {
		this.feDelNy = feDelNy;
	}

	public void setShSeason(Integer shSeason) {
		this.shSeason = shSeason;
	}
	public String getSpSeq() {
		return spSeq;
	}
	public void setSpSeq(String spSeq) {
		this.spSeq = spSeq;
	}

}
