package com.lalaping.mall.fishMappoint;

import org.springframework.stereotype.Repository;

@Repository
public interface FishMappointDao {
	public int mappointFishInsert(FishMappointDto fishMappointDto);
}
