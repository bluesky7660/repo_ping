package com.lalaping.mall.fish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		int result =  fishDao.update(fishDto);
//		fishDto.setMapPoint_mpSeq(fishDto.getMpSeq());
		List<String> seasonsSeq = fishDto.getSeasonsSeqList();
		fishDto.setFish_fsSeq(fishDto.getFsSeq());
		List<FishDto> seasonLists = fishDao.FishSeasonOneSelectList(fishDto);
		
		//추가
		int j = 0;
		for(String seasonSeq:seasonsSeq) {
			System.out.println("seasonSeq:"+seasonSeq);
			j++;
			fishDto.setSeason_ssSeq(seasonSeq);
	        fishDto.setSfOrder(j);
			fishDao.updateSeason(fishDto);
			boolean isExist = false;
			
			for(FishDto seasonList: seasonLists) {
				if(seasonList.getDelNy() ==0) {
					if(seasonSeq.equals(seasonList.getSeason_ssSeq())) {
						isExist = true;
						break;
					}
				}else if(seasonList.getDelNy() ==1){
					if(seasonSeq.equals(seasonList.getSeason_ssSeq())) {
						isExist = true;
						fishDto.setSeason_ssSeq(seasonSeq);
				        fishDto.setFish_fsSeq(seasonList.getFish_fsSeq());
				        fishDto.setSfOrder(j);
						fishDao.updateSeason(fishDto);
						break;
					}
				}
			}
			if (!isExist) {
				fishDto.setSeason_ssSeq(seasonSeq);
		        fishDto.setSfOrder(j);
		        fishDto.setFish_fsSeq(fishDto.getFsSeq());
		        fishDao.insertSeason(fishDto);
		    }
		}
		
		//삭제
		for (FishDto seasonList : seasonLists) {
		    boolean isExist = false;

		    for (String seasonSeq : seasonsSeq) {
		        if (seasonList.getSeason_ssSeq().equals(seasonSeq)) {
		        	isExist = true;
		            break;
		        }
		    }

		    if (!isExist) {
		    	fishDto.setSeason_ssSeq(seasonList.getSeason_ssSeq());  
		    	fishDto.setFish_fsSeq(seasonList.getFish_fsSeq()); 
		        fishDao.ueleteSeason(fishDto);
		    }
		}
		return result;
	}	
	public int uelete(FishDto fishDto) {
		return fishDao.uelete(fishDto);
	}
	public int delete(FishDto fishDto) {
		return fishDao.delete(fishDto);
	}
	public int listCount(FishVo vo) {
		return fishDao.listCount(vo);
	}
	
	//Season
	public int insertSeason(FishDto fishDto) {
		return fishDao.insertSeason(fishDto);
	}
	public int updateSeason(FishDto fishDto) {
		return fishDao.updateSeason(fishDto);
	}	
	public int ueleteSeason(FishDto fishDto) {
		return fishDao.ueleteSeason(fishDto);
	}

}
