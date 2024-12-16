package com.lalaping.common.base;

import com.lalaping.common.util.Constants;

public class BaseVo {
	//검색
	private String shValue; // 검색 키워드
    private Integer shOptionDate;	//검색 날짜 종류
//	    @DateTimeFormat(pattern = "yyyy-MM-dd")
//	    private Date dateStart;		//날짜 시작일
//	    @DateTimeFormat(pattern = "yyyy-MM-dd")
//	    private Date dateEnd;		//날짜 종료일
    private String shDateStart;		//날짜 시작일
    private String shDateEnd;		//날짜 종료일
    private Integer shOption;	//검색키워드 타입
    private Integer shDelNy;			//검색 조건[삭제] 
    private Integer shUseNy;			//검색 조건[사용]
    //페이지네이션
    private int thisPage = 1; 				//현재 페이지
    private int rowNumToShow = Constants.ROW_NUM_TO_SHOW;//	5;	// 화면에 보여줄 데이터 줄 갯수
	private int pageNumToShow = Constants.PAGE_NUM_TO_SHOW;//5;		// 화면에 보여줄 페이징 번호 갯수
    
    private int totalRows; 					//전체 데이터 갯수
    private int totalPages;					//전체페이지 번호
    private	int startPage;					//시작 페이지 번호
    private int endPage;					//마지막 페이지 번호
    
    private int startRnumForMysql = 0; 	// 쿼리 시작하는 row[index번호]
    private int sortOrder = 5;
    private String sortOrderString;
    private String shStaff;
    //-------------
    public void setParamsPaging(int totalRows) {
//			setThisPage(3);

		setTotalRows(totalRows);

		if (getTotalRows() == 0) {
			setTotalPages(1);
		} else {
			setTotalPages(getTotalRows() / getRowNumToShow());
		}
		System.out.println("TotalPages1:"+getTotalPages());
		if (getTotalRows() % getRowNumToShow() > 0) {
			setTotalPages(getTotalPages() + 1);
		}
		System.out.println("TotalPages2:"+getTotalPages());
		if (getTotalPages() < getThisPage()) {
			setThisPage(getTotalPages());
		}
		System.out.println("TotalPages3:"+getTotalPages());
		setStartPage(((getThisPage() - 1) / getPageNumToShow()) * getPageNumToShow() + 1);

		setEndPage(getStartPage() + getPageNumToShow() - 1);

		if (getEndPage() > getTotalPages()) {
			setEndPage(getTotalPages());
		}
		
		//오라클용
//			setEndRnumForOracle((getRowNumToShow() * getThisPage()));
//			setStartRnumForOracle((getEndRnumForOracle() - getRowNumToShow()) + 1);
//			if (getStartRnumForOracle() < 1) setStartRnumForOracle(1);
		
		if (thisPage == 1) {
			setStartRnumForMysql(0);
		} else {
			setStartRnumForMysql((getRowNumToShow() * (getThisPage()-1)));
		}
		
	}
//-------------------------
	public String getShValue() {
		return shValue;
	}
	public void setShValue(String shValue) {
		this.shValue = shValue;
	}
	public Integer getShOptionDate() {
		return shOptionDate;
	}
	public void setShOptionDate(Integer shOptionDate) {
		this.shOptionDate = shOptionDate;
	}
	public String getShDateStart() {
		return shDateStart;
	}
	public void setShDateStart(String shDateStart) {
		this.shDateStart = shDateStart;
	}
	public String getShDateEnd() {
		return shDateEnd;
	}
	public void setShDateEnd(String shDateEnd) {
		this.shDateEnd = shDateEnd;
	}
	public Integer getShOption() {
		return shOption;
	}
	public void setShOption(Integer shOption) {
		this.shOption = shOption;
	}
	public Integer getShDelNy() {
		return shDelNy;
	}
	public void setShDelNy(Integer shDelNy) {
		this.shDelNy = shDelNy;
	}
	public Integer getShUseNy() {
		return shUseNy;
	}
	public void setShUseNy(Integer shUseNy) {
		this.shUseNy = shUseNy;
	}
	public int getThisPage() {
		return thisPage;
	}
	public void setThisPage(int thisPage) {
		this.thisPage = thisPage;
	}
	public int getRowNumToShow() {
		return rowNumToShow;
	}
	public void setRowNumToShow(int rowNumToShow) {
		this.rowNumToShow = rowNumToShow;
	}
	public int getPageNumToShow() {
		return pageNumToShow;
	}
	public void setPageNumToShow(int pageNumToShow) {
		this.pageNumToShow = pageNumToShow;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getStartRnumForMysql() {
		return startRnumForMysql;
	}
	public void setStartRnumForMysql(int startRnumForMysql) {
		this.startRnumForMysql = startRnumForMysql;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getSortOrderString() {
		return sortOrderString;
	}
	public void setSortOrderString(String sortOrderString) {
		this.sortOrderString = sortOrderString;
	}
	public String getShStaff() {
		return shStaff;
	}
	public void setShStaff(String shStaff) {
		this.shStaff = shStaff;
	}
	    
}
