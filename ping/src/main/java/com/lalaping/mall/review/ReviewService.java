package com.lalaping.mall.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
	@Autowired
	ReviewDao reviewDao;
	
	public List<ReviewDto> rvSelectList(ReviewVo reviewVo){
		return reviewDao.rvSelectList(reviewVo);
	}
	public int rvinsert(ReviewDto reviewDto) {
		return reviewDao.rvinsert(reviewDto);
	}
	public ReviewDto rvSelectOne(ReviewDto reviewDto) {
		return reviewDao.rvSelectOne(reviewDto);
	}
	public int rvUpdate(ReviewDto reviewDto) {
		return reviewDao.rvUpdate(reviewDto);
	}
	public int rvUelete(ReviewDto reviewDto) {
		return reviewDao.rvUelete(reviewDto);
	}
	public int rvDelete(ReviewDto reviewDto) {
		return reviewDao.rvDelete(reviewDto);
	}
	public int selectOneCountRv(ReviewVo vo) {
		return reviewDao.selectOneCountRv(vo);
	}
	public List<ReviewDto> rvSelectListUsr(ReviewDto reviewDto){
		return reviewDao.rvSelectListUsr(reviewDto);
	}
	

}
