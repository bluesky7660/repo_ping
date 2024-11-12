package com.lalaping.infra.fish;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public interface FishDao {
	public List<FishDto> selectList(FishVo vo);
	public int insert(FishDto fishDto);
	public FishDto selectOne(FishDto fishDto);
	public int update(FishDto fishDto);
	public int uelete(FishDto fishDto);
	public int delete(FishDto fishDto);
	

}
