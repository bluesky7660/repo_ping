package com.lalaping.mall.fishMappoint;

import java.util.List;

public class FishMappointDto {
	private Integer fsOrder;
	private Integer delNy;
	private String mapPoint_mpSeq;
	private String mpSeq;
	private String fish_fsSeq;

	private List<String> fsSeqList;
//----------------------------------
	public Integer getFsOrder() {
		return fsOrder;
	}
	public void setFsOrder(Integer fsOrder) {
		this.fsOrder = fsOrder;
	}
	public Integer getDelNy() {
		return delNy;
	}
	public void setDelNy(Integer delNy) {
		this.delNy = delNy;
	}
	public String getMapPoint_mpSeq() {
		return mapPoint_mpSeq;
	}
	public void setMapPoint_mpSeq(String mapPoint_mpSeq) {
		this.mapPoint_mpSeq = mapPoint_mpSeq;
	}
	public String getFish_fsSeq() {
		return fish_fsSeq;
	}
	public void setFish_fsSeq(String fish_fsSeq) {
		this.fish_fsSeq = fish_fsSeq;
	}
	public List<String> getFsSeqList() {
		return fsSeqList;
	}
	public void setFsSeqList(List<String> fsSeqList) {
		this.fsSeqList = fsSeqList;
	}
	public String getMpSeq() {
		return mpSeq;
	}
	public void setMpSeq(String mpSeq) {
		this.mpSeq = mpSeq;
	}
	
}
