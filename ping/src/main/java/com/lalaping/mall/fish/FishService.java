package com.lalaping.mall.fish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FishService {
	@Autowired
	public FishDao fishDao;
	
	public List<FishDto> selectList(FishVo vo){
		return fishDao.selectList(vo);
	}	
	public List<FishDto> shipFishList(FishVo vo){
		return fishDao.shipFishList(vo);
	}
	public int insert(FishDto fishDto) {
		return fishDao.insert(fishDto);
	}
	public FishDto selectOne(FishDto fishDto) {
		return fishDao.selectOne(fishDto);
	}
	public int update(FishDto fishDto) {
		return fishDao.update(fishDto);
	}	
	public int uelete(FishDto fishDto) {
		return fishDao.uelete(fishDto);
	}
	public int delete(FishDto fishDto) {
		return fishDao.delete(fishDto);
	}

}
