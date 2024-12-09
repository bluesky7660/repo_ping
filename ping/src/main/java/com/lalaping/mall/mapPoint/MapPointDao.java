package com.lalaping.mall.mapPoint;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MapPointDao {
	public List<MapPointDto> selectList(MapPointVo vo);
	public List<MapPointDto> selectUsrList(MapPointVo vo);
	public List<MapPointDto> selectSearchList(MapPointVo vo);
	public List<MapPointDto> allList(MapPointVo vo);
	public List<MapPointDto> nearList(MapPointVo vo);
	public List<MapPointDto> portNearList(MapPointVo vo);
	public List<MapPointDto> sessSelectList(MapPointVo vo);
	
	public int portNearCount(MapPointVo mapPointVo);
	public int insert(MapPointDto mapPointDto);
	public MapPointDto selectOne(MapPointDto mapPointDto);
	public MapPointDto selectUsrOne(MapPointDto mapPointDto);
	public int update(MapPointDto mapPointDto);
	public int uelete(MapPointDto mapPointDto);
	public int delete(MapPointDto mapPointDto);
	public int listCount(MapPointVo vo);


}
