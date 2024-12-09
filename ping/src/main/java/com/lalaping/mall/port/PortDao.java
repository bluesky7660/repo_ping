package com.lalaping.mall.port;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lalaping.mall.ship.ShipDto;

@Repository
public interface PortDao {
	public List<PortDto> selectList(PortVo vo);
	public int insert(PortDto portDto);
	public PortDto selectOne(PortDto portDto);
	public int update(PortDto portDto);
	public int uelete(PortDto portDto);
	public int delete(PortDto portDto);
	public int selectOneCount(PortVo vo);
	public int listCount(PortVo vo);

	public int insertUploaded(PortDto portDto);

}
