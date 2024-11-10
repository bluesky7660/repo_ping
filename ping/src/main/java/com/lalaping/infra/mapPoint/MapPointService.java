package com.lalaping.infra.mapPoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lalaping.infra.port.PortDao;
import com.lalaping.infra.port.PortDto;
import com.lalaping.infra.port.PortVo;

@Service
public class MapPointService {
	@Autowired
	public MapPointDao mapPointDao;
	
	public List<MapPointDto> selectList(MapPointVo vo) {
		return mapPointDao.selectList(vo);
	}
	public int insert(MapPointDto mapPointDto) {
		return mapPointDao.insert(mapPointDto);
	}
	public MapPointDto selectOne(MapPointDto mapPointDto) {
		return mapPointDao.selectOne(mapPointDto);
	}
	public int update(MapPointDto mapPointDto) {
		return mapPointDao.update(mapPointDto);
	}
	public int uelete(MapPointDto mapPointDto) {
		return mapPointDao.uelete(mapPointDto);
	}
	public int delete(MapPointDto mapPointDto) {
		return mapPointDao.delete(mapPointDto);
	}

}
