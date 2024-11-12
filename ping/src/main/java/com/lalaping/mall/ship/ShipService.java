package com.lalaping.mall.ship;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipService {
	@Autowired
	public ShipDao shipDao;
	
	public List<ShipDto> selectList(ShipVo vo){
		return shipDao.selectList(vo);
	}
	public int insert(ShipDto shipDto) {
		return shipDao.insert(shipDto);
	}
	public ShipDto selectOne(ShipDto shipDto) {
		return shipDao.selectOne(shipDto);
	}
	public int update(ShipDto shipDto) {
		return shipDao.update(shipDto);
	}
	public int uelete(ShipDto shipDto) {
		return shipDao.uelete(shipDto);
	}
	public int delete(ShipDto shipDto) {
		return shipDao.delete(shipDto);
	}


}
