package com.lalaping.mall.fishShip;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lalaping.mall.fishMappoint.FishMappointDto;

@Repository
public interface FishShipDao {
	public List<FishShipDto> FishShipOneSelectList(FishShipDto fishShipDto);
	public int ShipFishInsert(FishShipDto fishShipDto);
	public int uelete(FishShipDto fishShipDto);
	public int update(FishShipDto fishShipDto);
	public int orderUpdate(FishShipDto fishShipDto);
}
