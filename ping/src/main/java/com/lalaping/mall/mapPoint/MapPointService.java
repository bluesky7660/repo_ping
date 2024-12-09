package com.lalaping.mall.mapPoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapPointService {
	@Autowired
	public MapPointDao mapPointDao;
	
	public List<MapPointDto> selectList(MapPointVo vo) {
		return mapPointDao.selectList(vo);
	}
	public List<MapPointDto> selectUsrList(MapPointVo vo) {
		return mapPointDao.selectUsrList(vo);
	}
	public List<MapPointDto> selectSearchList(MapPointVo vo) {
		return mapPointDao.selectSearchList(vo);
	}
	public List<MapPointDto> allList(MapPointVo vo) {
		return mapPointDao.allList(vo);
	}
	public List<MapPointDto> nearList(MapPointVo vo) {
		return mapPointDao.nearList(vo);
	}
	public List<MapPointDto> portNearList(MapPointVo vo) {
		return mapPointDao.portNearList(vo);
	}
	public List<MapPointDto> sessSelectList(MapPointVo vo) {
		return mapPointDao.sessSelectList(vo);
	}
	public int portNearCount(MapPointVo mapPointVo) {
		return mapPointDao.portNearCount(mapPointVo);
	}
	public int insert(MapPointDto mapPointDto) {
		return mapPointDao.insert(mapPointDto);
	}
	public MapPointDto selectOne(MapPointDto mapPointDto) {
		return mapPointDao.selectOne(mapPointDto);
	}
	public MapPointDto selectUsrOne(MapPointDto mapPointDto) {
		return mapPointDao.selectUsrOne(mapPointDto);
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
	public int listCount(MapPointVo vo) {
		return mapPointDao.listCount(vo);
	}

}
