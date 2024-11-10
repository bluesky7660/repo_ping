package com.lalaping.infra.ship;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lalaping.infra.port.PortDto;
import com.lalaping.infra.port.PortVo;

@Repository
public interface ShipDao {
	public List<ShipDto> selectList(ShipVo vo);
	public int insert(ShipDto shipDto);
	public ShipDto selectOne(ShipDto shipDto);
	public int update(ShipDto shipDto);
	public int uelete(ShipDto shipDto);
	public int delete(ShipDto shipDto);

}
