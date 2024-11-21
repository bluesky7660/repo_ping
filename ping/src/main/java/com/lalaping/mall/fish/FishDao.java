package com.lalaping.mall.fish;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public interface FishDao {
	public List<FishDto> selectList(FishVo vo);
	public List<FishDto> shipFishList(FishVo vo);
	public List<FishDto> allList(FishVo vo);
	public List<FishDto> allOneList(FishVo vo);
	public int insert(FishDto fishDto);
	public FishDto selectOne(FishDto fishDto);
	public int update(FishDto fishDto);
	public int uelete(FishDto fishDto);
	public int delete(FishDto fishDto);
	

}
