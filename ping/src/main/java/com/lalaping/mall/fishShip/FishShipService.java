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
		int i= 0;
		for (String fsSeq:fishShipDto.getFsSeqList()) {
			i=+1;
			System.out.println("fsOrder:"+i);
			System.out.println("fish_fsSeq[getFsSeqList]:"+fsSeq);
			System.out.println("delNy:"+0);
			System.out.println("ship_spSeq:"+fishShipDto.getShip_spSeq());
		}
		
		int a = fishShipDao.ShipFishInsert(fishShipDto);
		return a;
	}
}
