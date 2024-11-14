package com.lalaping.mall.port;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortService {
	@Autowired
	public PortDao portDao;
	
	public List<PortDto> selectList(PortVo vo) {
		return portDao.selectList(vo);
	}
	public int insert(PortDto portDto) {
		return portDao.insert(portDto);
	}
	public PortDto selectOne(PortDto portDto) {
		return portDao.selectOne(portDto);
	}
	public int update(PortDto portDto) {
		return portDao.update(portDto);
	}
	public int uelete(PortDto portDto) {
		return portDao.uelete(portDto);
	}
	public int delete(PortDto portDto) {
		return portDao.delete(portDto);
	}
	public int selectOneCount(PortVo vo) {
		return portDao.selectOneCount(vo);
	}
	

}
