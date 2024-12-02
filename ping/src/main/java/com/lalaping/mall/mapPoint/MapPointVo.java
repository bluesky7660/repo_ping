package com.lalaping.mall.mapPoint;

import java.util.List;

import com.lalaping.common.base.BaseVo;

public class MapPointVo extends BaseVo {
	private Integer shType;
	private String season_ssSeq;
	private List<String> fsSeqList;
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

//	public List<Integer> getFsSeqList() {
//		return fsSeqList;
//	}
//
//	public void setFsSeqList(List<Integer> fsSeqList) {
//		this.fsSeqList = fsSeqList;
//	}
	
	

}
