package com.lalaping.mall.mapPoint;

import java.util.Date;

import com.lalaping.common.base.FileDto;

public class MapPointDto extends FileDto {
	private String mpSeq;
	private String mpTitle;
	private String mpText;
	private String mpAddress;
	private Integer mpType;
	private Double mpLatitude;
	private Double mpLongitude;
	private Integer mpDelNy;
	private Date mpRegDate;
	
	private String fsNameList;
	private String fsSeqList;
	private String season_ssSeq;
	private Double distanceIn;
//	---------------------------------
	
	public String getMpSeq() {
		return mpSeq;
	}
	public void setMpSeq(String mpSeq) {
		this.mpSeq = mpSeq;
	}
	public String getMpTitle() {
		return mpTitle;
	}
	public void setMpTitle(String mpTitle) {
		this.mpTitle = mpTitle;
	}
	public String getMpText() {
		return mpText;
	}
	public void setMpText(String mpText) {
		this.mpText = mpText;
	}
	public String getMpAddress() {
		return mpAddress;
	}
	public void setMpAddress(String mpAddress) {
		this.mpAddress = mpAddress;
	}
	public Integer getMpType() {
		return mpType;
	}
	public void setMpType(Integer mpType) {
		this.mpType = mpType;
	}
	public Double getMpLatitude() {
		return mpLatitude;
	}
	public void setMpLatitude(Double mpLatitude) {
		this.mpLatitude = mpLatitude;
	}
	public Double getMpLongitude() {
		return mpLongitude;
	}
	public void setMpLongitude(Double mpLongitude) {
		this.mpLongitude = mpLongitude;
	}
	public Integer getMpDelNy() {
		return mpDelNy;
	}
	public void setMpDelNy(Integer mpDelNy) {
		this.mpDelNy = mpDelNy;
	}
	public Date getMpRegDate() {
		return mpRegDate;
	}
	public void setMpRegDate(Date mpRegDate) {
		this.mpRegDate = mpRegDate;
	}
	public String getFsNameList() {
		return fsNameList;
	}
	public void setFsNameList(String fsNameList) {
		this.fsNameList = fsNameList;
	}
	public String getSeason_ssSeq() {
		return season_ssSeq;
	}
	public void setSeason_ssSeq(String season_ssSeq) {
		this.season_ssSeq = season_ssSeq;
	}
	public Double getDistanceIn() {
		return distanceIn;
	}
	public void setDistanceIn(Double distanceIn) {
		this.distanceIn = distanceIn;
	}
	public String getFsSeqList() {
		return fsSeqList;
	}
	public void setFsSeqList(String fsSeqList) {
		this.fsSeqList = fsSeqList;
	}

}
