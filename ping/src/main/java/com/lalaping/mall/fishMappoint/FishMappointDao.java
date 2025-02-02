package com.lalaping.mall.fishMappoint;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface FishMappointDao {
	public List<FishMappointDto> FishMapOneSelectList(FishMappointDto fishMappointDto);
	public int mappointFishInsert(FishMappointDto fishMappointDto);
	public int update(FishMappointDto fishMappointDto);
	public int uelete(FishMappointDto fishMappointDto);
}
