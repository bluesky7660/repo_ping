package com.lalaping.mall.ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.lalaping.mall.fish.FishDto;

public class ShipDto {
	private String fsSeq;
	private String fsName;
	private String spSeq;
	private String spName;
	private String spPrice;
	private String spDesc;
	private String spTotal;
	private String spRemain;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date spStart;
	private Integer spDelNy;
	private Integer spArea;
	private Integer spRentalNy;
	private Integer spEatNy;
	private Date spRegDate;
	private String port_ptSeq;
	
	private String ptName;
	
//	private List<FishDto> fishList;
	private List<String> fsSeqList;
	private List<String> fsNameList;
	private String fsNames;

	
//	-------------------------------
	
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
	public String getSpSeq() {
		return spSeq;
	}
	public void setSpSeq(String spSeq) {
		this.spSeq = spSeq;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getSpPrice() {
		return spPrice;
	}
	public void setSpPrice(String spPrice) {
		this.spPrice = spPrice;
	}
	public String getSpDesc() {
		return spDesc;
	}
	public void setSpDesc(String spDesc) {
		this.spDesc = spDesc;
	}
	public String getSpTotal() {
		return spTotal;
	}
	public void setSpTotal(String spTotal) {
		this.spTotal = spTotal;
	}
	public Date getSpStart() {
		return spStart;
	}
	public void setSpStart(Date spStart) {
		this.spStart = spStart;
	}
	public Integer getSpDelNy() {
		return spDelNy;
	}
	public void setSpDelNy(Integer spDelNy) {
		this.spDelNy = spDelNy;
	}
	
	public Integer getSpArea() {
		return spArea;
	}
	public void setSpArea(Integer spArea) {
		this.spArea = spArea;
	}
	public Integer getSpRentalNy() {
		return spRentalNy;
	}
	public void setSpRentalNy(Integer spRentalNy) {
		this.spRentalNy = spRentalNy;
	}
	public Integer getSpEatNy() {
		return spEatNy;
	}
	public void setSpEatNy(Integer spEatNy) {
		this.spEatNy = spEatNy;
	}
	public Date getSpRegDate() {
		return spRegDate;
	}
	public void setSpRegDate(Date spRegDate) {
		this.spRegDate = spRegDate;
	}
	public String getPort_ptSeq() {
		return port_ptSeq;
	}
	public void setPort_ptSeq(String port_ptSeq) {
		this.port_ptSeq = port_ptSeq;
	}

	public List<String> getFsSeqList() {
		return fsSeqList;
	}
	public void setFsSeqList(String fsSeqList) {
        if (fsSeqList != null && !fsSeqList.isEmpty()) {
            this.fsSeqList = Arrays.asList(fsSeqList.split(","));
        } else {
            this.fsSeqList = new ArrayList<>();
        }
    }
	public List<String> getFsNameList() {
		return fsNameList;
	}
	public void setFsNameList(String fsNameList) {
        if (fsNameList != null && !fsNameList.isEmpty()) {
            this.fsNameList = Arrays.asList(fsNameList.split(","));
        } else {
            this.fsNameList = new ArrayList<>();
        }
    }
	public String getPtName() {
		return ptName;
	}
	public void setPtName(String ptName) {
		this.ptName = ptName;
	}
	public String getSpRemain() {
		return spRemain;
	}
	public void setSpRemain(String spRemain) {
		this.spRemain = spRemain;
	}
	
	

	
}
