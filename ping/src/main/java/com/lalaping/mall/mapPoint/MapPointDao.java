package com.lalaping.mall.mapPoint;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lalaping.mall.port.PortDto;

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
	public int nearCount(MapPointVo mapPointVo);
	public int sessSelectCount(MapPointVo mapPointVo);
	public int selectUsrCount(MapPointVo mapPointVo);
	public int insert(MapPointDto mapPointDto);
	public MapPointDto selectOne(MapPointDto mapPointDto);
	public MapPointDto selectUsrOne(MapPointDto mapPointDto);
	public MapPointDto lastPoint();
	public int update(MapPointDto mapPointDto);
	public int uelete(MapPointDto mapPointDto);
	public int delete(MapPointDto mapPointDto);
	public int listCount(MapPointVo vo);

	public int insertUploaded(MapPointDto mapPointDto);


}
