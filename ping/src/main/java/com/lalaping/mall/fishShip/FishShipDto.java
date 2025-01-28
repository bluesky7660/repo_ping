package com.lalaping.mall.fishShip;

import java.util.List;

public class FishShipDto {
	private Integer fsOrder;
	private Integer delNy;
	private String ship_spSeq;
	private String spSeq;
	private String fish_fsSeq;

	private List<String> fsSeqList;
//-------------------------------------

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

	public String getShip_spSeq() {
		return ship_spSeq;
	}

	public void setShip_spSeq(String ship_spSeq) {
		this.ship_spSeq = ship_spSeq;
	}

	public String getSpSeq() {
		return spSeq;
	}

	public void setSpSeq(String spSeq) {
		this.spSeq = spSeq;
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
	
}
