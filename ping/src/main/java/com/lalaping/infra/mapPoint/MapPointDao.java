package com.lalaping.infra.mapPoint;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lalaping.infra.port.PortDto;
import com.lalaping.infra.port.PortVo;
@Repository
public interface MapPointDao {
	public List<MapPointDto> selectList(MapPointVo vo);
	public int insert(MapPointDto mapPointDto);
	public MapPointDto selectOne(MapPointDto mapPointDto);
	public int update(MapPointDto mapPointDto);
	public int uelete(MapPointDto mapPointDto);
	public int delete(MapPointDto mapPointDto);


}
