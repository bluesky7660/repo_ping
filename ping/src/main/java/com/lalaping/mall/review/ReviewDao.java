package com.lalaping.mall.review;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDao {
	public List<ReviewDto> rvSelectList(ReviewVo reviewVo);
	public int rvinsert(ReviewDto reviewDto);
	public ReviewDto rvSelectOne(ReviewDto reviewDto);
	public int rvUpdate(ReviewDto reviewDto);
	public int rvUelete(ReviewDto reviewDto);
	public int rvDelete(ReviewDto reviewDto);
	public int selectOneCountRv(ReviewVo vo);
	public List<ReviewDto> rvSelectListUsr(ReviewDto reviewDto);
	
	public int rvSelectListCount(ReviewVo vo);

}
