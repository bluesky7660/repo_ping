package com.lalaping.mall.fishMappoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lalaping.mall.fish.FishDao;
import com.lalaping.mall.mapPoint.MapPointDao;

@Service
public class FishMappointService {
	@Autowired
	FishDao fishDao;
	
	@Autowired
	MapPointDao mapPointDao;
	
	@Autowired
	FishMappointDao fishMappointDao;
	
	public int mappointFishInsert(FishMappointDto fishMappointDto) {
		int i= 0;
		for (String fsSeq:fishMappointDto.getFsSeqList()) {
			i=+1;
			System.out.println("fsOrder:"+i);
			System.out.println("fish_fsSeq[getFsSeqList]:"+fsSeq);
			System.out.println("delNy:"+0);
			System.out.println("mapPoint_mpSeq:"+fishMappointDto.getMapPoint_mpSeq());
		}
		
		int a = fishMappointDao.mappointFishInsert(fishMappointDto);
		return a;
	}
}
