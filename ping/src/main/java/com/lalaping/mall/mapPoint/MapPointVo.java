package com.lalaping.mall.mapPoint;

import java.util.List;

import com.lalaping.common.base.BaseVo;

public class MapPointVo extends BaseVo {
	private Integer shType;
	private String season_ssSeq;
	private List<String> fsSeqList;
	private Double baseMpLongitude;
	private Double baseMpLatitude;
	private String shMpValue;
	private String mpSeq;
	
	private String ptSeq;
	private String shSeason;
//	------------------------------------

	public Integer getShType() {
		return shType;
	}

	public void setShType(Integer shType) {
		this.shType = shType;
	}

	public String getSeason_ssSeq() {
		return season_ssSeq;
	}

	public void setSeason_ssSeq(String season_ssSeq) {
		this.season_ssSeq = season_ssSeq;
	}

	public List<String> getFsSeqList() {
		return fsSeqList;
	}

	public void setFsSeqList(List<String> fsSeqList) {
		this.fsSeqList = fsSeqList;
	}

	public Double getBaseMpLongitude() {
		return baseMpLongitude;
	}

	public void setBaseMpLongitude(Double baseMpLongitude) {
		this.baseMpLongitude = baseMpLongitude;
	}

	public Double getBaseMpLatitude() {
		return baseMpLatitude;
	}

	public void setBaseMpLatitude(Double baseMpLatitude) {
		this.baseMpLatitude = baseMpLatitude;
	}
	

	public String getShMpValue() {
		return shMpValue;
	}

	public void setShMpValue(String shMpValue) {
		this.shMpValue = shMpValue;
	}

	public String getPtSeq() {
		return ptSeq;
	}

	public void setPtSeq(String ptSeq) {
		this.ptSeq = ptSeq;
	}

	public String getShSeason() {
		return shSeason;
	}

	public void setShSeason(String shSeason) {
		this.shSeason = shSeason;
	}

	public String getMpSeq() {
		return mpSeq;
	}

	public void setMpSeq(String mpSeq) {
		this.mpSeq = mpSeq;
	}


}
