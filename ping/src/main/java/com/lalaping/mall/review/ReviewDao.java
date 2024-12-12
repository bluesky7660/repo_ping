package com.lalaping.mall.review;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDao {
	public List<ReviewDto> rvSelectList(ReviewVo reviewVo);
	public int rvinsert(ReviewDto reviewDto);
	public ReviewDto rvSelectOne(ReviewDto reviewDto);

}
