package com.lalaping.mall.port;

import com.lalaping.common.base.BaseVo;

public class PortVo extends BaseVo {
	private String shPtArea;
	
	 // Paging
    private int thisPage = 1; // 현재 페이지
    private int rowNumToShow = 10; // 화면에 보여줄 데이터 줄 갯수
    private int pageNumToShow = 5; // 화면에 보여줄 페이징 번호 갯수

    private int totalRows; // 전체 데이터 갯수
    private int totalPages; // 전체 페이지 번호
    private int startPage; // 시작 페이지 번호
    private int endPage; // 마지막 페이지 번호

    private int startRnumForMysql = 0; // 쿼리 시작 row
	
//	-----------------------------------

	public String getShPtArea() {
		return shPtArea;
	}

	public void setShPtArea(String shPtArea) {
		this.shPtArea = shPtArea;
	}
	
//	-----------------page--------------------
	

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
	
	
	public void setParamsPaging(int totalRows) {
        setTotalRows(totalRows);

        if (getTotalRows() == 0) {
            setTotalPages(1);
        } else {
            setTotalPages(getTotalRows() / getRowNumToShow());
        }

        if (getTotalRows() % getRowNumToShow() > 0) {
            setTotalPages(getTotalPages() + 1);
        }

        if (getTotalPages() < getThisPage()) {
            setThisPage(getTotalPages());
        }

        setStartPage(((getThisPage() - 1) / getPageNumToShow()) * getPageNumToShow() + 1);
        setEndPage(getStartPage() + getPageNumToShow() - 1);

        if (getEndPage() > getTotalPages()) {
            setEndPage(getTotalPages());
        }

        if (thisPage == 1) {
            setStartRnumForMysql(0);
        } else {
            setStartRnumForMysql((getRowNumToShow() * (getThisPage() - 1)));
        }
    }
	

	
	
	
	


	
	

	
	
	
	
	

}
