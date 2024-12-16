package com.lalaping.mall.ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.lalaping.common.base.FileDto;

public class ShipDto extends FileDto {
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
	private Boolean spDelNy;
	private String spArea;
	private Boolean spRentalNy;
	private Boolean spEatNy;
	private Date spRegDate;
	private String port_ptSeq;
	private String seq;
	private String pSeq;
	private String path;

	private String ptSeq;
	private String ptName;
	private Double ptLongitude;
	private Double ptLatitude;
	
//	private List<FishDto> fishList;
//	private List<String> fsSeqList;
//	private List<String> fsNameList;
	private String fsSeqList;
	private String fsNameList;
	private String fsNames;
	
//	-------------------------------
	
	public String getFsSeq() {
		return fsSeq;
	}
	public String getPtSeq() {
		return ptSeq;
	}
	public void setPtSeq(String ptSeq) {
		this.ptSeq = ptSeq;
	}
	public String getFsNames() {
		return fsNames;
	}
	public void setFsNames(String fsNames) {
		this.fsNames = fsNames;
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
	public Boolean getSpDelNy() {
		return spDelNy;
	}
	public void setSpDelNy(Boolean spDelNy) {
		this.spDelNy = spDelNy;
	}
	public String getSpArea() {
		return spArea;
	}
	public void setSpArea(String spArea) {
		this.spArea = spArea;
	}
	public Boolean getSpRentalNy() {
		return spRentalNy;
	}
	public void setSpRentalNy(Boolean spRentalNy) {
		this.spRentalNy = spRentalNy;
	}
	public Boolean getSpEatNy() {
		return spEatNy;
	}
	public void setSpEatNy(Boolean spEatNy) {
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
//	public List<String> getFsSeqList() {
//		return fsSeqList;
//	}
//	public void setFsSeqList(String fsSeqList) {
//        if (fsSeqList != null && !fsSeqList.isEmpty()) {
//            this.fsSeqList = Arrays.asList(fsSeqList.split(","));
//        } else {
//            this.fsSeqList = new ArrayList<>();
//        }
//    }
//	public List<String> getFsNameList() {
//		return fsNameList;
//	}
//	public void setFsNameList(String fsNameList) {
//        if (fsNameList != null && !fsNameList.isEmpty()) {
//            this.fsNameList = Arrays.asList(fsNameList.split(","));
//        } else {
//            this.fsNameList = new ArrayList<>();
//        }
//    }
	
	public String getFsSeqList() {
		return fsSeqList;
	}
	public void setFsSeqList(String fsSeqList) {
		this.fsSeqList = fsSeqList;
	}
	public String getFsNameList() {
		return fsNameList;
	}
	public void setFsNameList(String fsNameList) {
		this.fsNameList = fsNameList;
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
	public Double getPtLongitude() {
		return ptLongitude;
	}
	public void setPtLongitude(Double ptLongitude) {
		this.ptLongitude = ptLongitude;
	}
	public Double getPtLatitude() {
		return ptLatitude;
	}
	public void setPtLatitude(Double ptLatitude) {
		this.ptLatitude = ptLatitude;
	}
	public String getpSeq() {
		return pSeq;
	}
	public void setpSeq(String pSeq) {
		this.pSeq = pSeq;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	
	

}