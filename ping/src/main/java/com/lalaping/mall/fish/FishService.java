package com.lalaping.mall.fish;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lalaping.infra.code.CodeDto;

import jakarta.annotation.PostConstruct;

@Service
public class FishService {
	@Autowired
	public FishDao fishDao;
	
	public List<FishDto> selectList(FishVo vo){
		return fishDao.selectList(vo);
	}	
	public List<FishDto> shipFishList(FishVo vo){
		return fishDao.shipFishList(vo);
	}
	public List<FishDto> allList(FishVo vo){
		return fishDao.allList(vo);
	}
	public List<FishDto> allOneList(FishVo vo){
		return fishDao.allOneList(vo);
	}
	public int insert(FishDto fishDto) {
		return fishDao.insert(fishDto);
	}
	public FishDto selectOne(FishDto fishDto) {
		return fishDao.selectOne(fishDto);
	}
	public int update(FishDto fishDto) {
		return fishDao.update(fishDto);
	}	
	public int uelete(FishDto fishDto) {
		return fishDao.uelete(fishDto);
	}
	public int delete(FishDto fishDto) {
		return fishDao.delete(fishDto);
	}
	
	
//	@PostConstruct
//	public void selectListCachedCodeArrayList() throws Exception {
//		List<CodeDto> codeListFromDb = (ArrayList<CodeDto>) codeDao.selectListCachedCodeArrayList();
//		CodeDto.cachedCodeArrayList.clear(); 
//		CodeDto.cachedCodeArrayList.addAll(codeListFromDb);
//		System.out.println("cachedCodeArrayList: " + CodeDto.cachedCodeArrayList.size() + " chached !");
//	}
//	
//	public static void clear() throws Exception {
//		CodeDto.cachedCodeArrayList.clear();
//	}
//	
//	public static List<FishDto> shipSelectListFish(String ship_spSeq) throws Exception {
//		List<FishDto> rt = new ArrayList<FishDto>();
//		for(FishDto fishRow : FishDto.cachedFishArrayList) {
//			if (fishRow.getShip_spSeq().equals(ship_spSeq)) {
//				rt.add(fishRow);
//			} else {
//				// by pass
//			}
//		}
//		return rt;
//	}

}
