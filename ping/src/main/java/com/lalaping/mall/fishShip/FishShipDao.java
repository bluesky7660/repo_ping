package com.lalaping.mall.fishShip;

import org.springframework.stereotype.Repository;

import com.lalaping.mall.fishMappoint.FishMappointDto;

@Repository
public interface FishShipDao {
	
	public int ShipFishInsert(FishShipDto fishShipDto);
}
