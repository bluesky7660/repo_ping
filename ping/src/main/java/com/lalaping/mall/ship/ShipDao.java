package com.lalaping.mall.ship;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public interface ShipDao {
	public List<ShipDto> selectList(ShipVo vo);
	public List<ShipDto> selectUsrList(ShipVo vo);
	public int insert(ShipDto shipDto);
	public ShipDto selectOne(ShipDto shipDto);
	public int update(ShipDto shipDto);
	public int uelete(ShipDto shipDto);
	public int delete(ShipDto shipDto);

}