package com.lalaping.mall.fishShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lalaping.mall.fish.FishDao;
import com.lalaping.mall.ship.ShipDao;

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
}
