package com.lalaping.mall.ship;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public interface ShipDao {
	public List<ShipDto> selectList(ShipVo vo);
	public List<ShipDto> selectUsrList(ShipVo vo);
	public List<ShipDto> portSelectList(ShipVo vo);
	public List<ShipDto> otherPortSelectList(ShipVo vo);
	public int listCount(ShipVo vo);
	public int portListCount(ShipVo vo);
	public int insert(ShipDto shipDto);
	public ShipDto selectOne(ShipDto shipDto);
	public int update(ShipDto shipDto);
	public int uelete(ShipDto shipDto);
	public int delete(ShipDto shipDto);
	
	public int insertUploaded(ShipDto shipDto);
	public List<ShipDto> selectUsrList2(ShipVo vo);
	public List<ShipDto> selectUsrList3(ShipVo vo);

}
