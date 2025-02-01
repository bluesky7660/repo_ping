package com.lalaping.mall.fishShip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lalaping.mall.fish.FishDao;
import com.lalaping.mall.ship.ShipDao;
import com.lalaping.mall.ship.ShipDto;

@Service
public class FishShipService {
	@Autowired
	FishDao fishDao;
	
	@Autowired
	ShipDao shipDao;
	
	@Autowired
	FishShipDao fishShipDao;
	
	public int ShipFishInsert(FishShipDto fishShipDto) {
		int a = fishShipDao.ShipFishInsert(fishShipDto);
		return a;
	}
	public int uelete(FishShipDto fishShipDto) {
		return fishShipDao.uelete(fishShipDto);
	}
	public int update(FishShipDto fishShipDto) {
		return fishShipDao.update(fishShipDto);
	}
	public int orderUpdate(FishShipDto fishShipDto) {
		return fishShipDao.orderUpdate(fishShipDto);
	}
	
	public List<FishShipDto> FishShipOneSelectList(FishShipDto fishShipDto){
		return fishShipDao.FishShipOneSelectList(fishShipDto);
	}
}
