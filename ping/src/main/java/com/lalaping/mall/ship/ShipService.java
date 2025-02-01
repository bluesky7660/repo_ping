package com.lalaping.mall.ship;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.lalaping.common.config.S3Config;
import com.lalaping.common.util.UtilDateTime;
import com.lalaping.mall.fishShip.FishShipDao;
import com.lalaping.mall.fishShip.FishShipDto;

@Service
public class ShipService {
	@Autowired
	public ShipDao shipDao;
	
	@Autowired
	public FishShipDao fishShipDao;
	
	@Autowired
	S3Config s3Config;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	public List<ShipDto> selectList(ShipVo vo){
		return shipDao.selectList(vo);
	}
	public List<ShipDto> selectUsrList(ShipVo vo){
		return shipDao.selectUsrList(vo);
	}
	public List<ShipDto> portSelectList(ShipVo vo){
		return shipDao.portSelectList(vo);
	}
	public List<ShipDto> otherPortSelectList(ShipVo vo){
		return shipDao.otherPortSelectList(vo);
	}
	public int listCount(ShipVo vo) {
		return shipDao.listCount(vo);
	}
	public int portListCount(ShipVo vo) {
		return shipDao.portListCount(vo);
	}
	public int insert(ShipDto shipDto, FishShipDto fishShipDto) throws Exception {
		int result =shipDao.insert(shipDto);
		MultipartFile[] multipartFiles = shipDto.getUploadFiles();
		int maxNumber = multipartFiles.length;
		AmazonS3Client amazonS3Client = s3Config.amazonS3Client();
		String seq = shipDto.getSpSeq();
		for(int i=0; i<multipartFiles.length; i++) {
			
			if(!multipartFiles[i].isEmpty()) {
				int type = 1;
				int defaultNy = 1;
				String classOrName = shipDto.getClass().getSimpleName().toString().toLowerCase();
				String className = classOrName.substring(0, classOrName.length() - 3);
				System.out.println("Class Name: " + className);

				String fileName = multipartFiles[i].getOriginalFilename();
				System.out.println("Original File Name: " + fileName);
				
				System.out.println("File Type: " + type);
				
				String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
				System.out.println("File Extension: " + ext);

				String uuid = UUID.randomUUID().toString();
				System.out.println("UUID: " + uuid);

				String uuidFileName = uuid + "." + ext;
				System.out.println("UUID File Name: " + uuidFileName);

				String pathModule = className;
				System.out.println("Path Module: " + pathModule);

				String nowString = UtilDateTime.nowString();
				System.out.println("Current Date and Time: " + nowString);

				String pathDate = nowString.substring(0,4) + "/" + nowString.substring(5,7) + "/" + nowString.substring(8,10);
				System.out.println("Path Date: " + pathDate);

				String path = pathModule + "/" + type + "/" + pathDate + "/";
				System.out.println("Final Path: " + path);

		        ObjectMetadata metadata = new ObjectMetadata();
		        metadata.setContentLength(multipartFiles[i].getSize());
		        metadata.setContentType(multipartFiles[i].getContentType());
		        
		        amazonS3Client.putObject(bucket, path + uuidFileName, multipartFiles[i].getInputStream(), metadata);
				
		        String objectUrl = amazonS3Client.getUrl(bucket, path + uuidFileName).toString();
		        
				shipDto.setPath(objectUrl);
				shipDto.setOriginalName(fileName);
				shipDto.setUuid(uuidFileName);
				shipDto.setExt(ext);
				shipDto.setSize(multipartFiles[i].getSize());
				
//				shipDto.setTableName(tableName);
				shipDto.setType(type);
				shipDto.setDefaultNy(defaultNy);
				shipDto.setSort(maxNumber + i);
				System.out.println("getSeq "+i+"번: " + shipDto.getSpSeq());
				shipDto.setPseq(seq);
				
				shipDao.insertUploaded(shipDto);
			}
			
		}
		fishShipDto.setShip_spSeq(shipDto.getSpSeq());
		int i=0;
		for(String fsSeq :fishShipDto.getFsSeqList()) {
			i=+1;
			System.out.println("fsOrder:"+i);
			System.out.println("fish_fsSeq[getFsSeqList]:"+fsSeq);
			System.out.println("delNy:"+0);
			System.out.println("Ship_spSeq:"+fishShipDto.getShip_spSeq());
			fishShipDto.setFsOrder(i);
			fishShipDto.setFish_fsSeq(fsSeq);
			fishShipDao.ShipFishInsert(fishShipDto);
		}
		return result;
	}
	public ShipDto selectOne(ShipDto shipDto) {
		return shipDao.selectOne(shipDto);
	}
	public int update(ShipDto shipDto, FishShipDto fishShipDto) throws Exception {
		int result =shipDao.update(shipDto);
		MultipartFile[] multipartFiles = shipDto.getUploadFiles();
		int maxNumber = multipartFiles.length;
		AmazonS3Client amazonS3Client = s3Config.amazonS3Client();
		String seq = shipDto.getSpSeq();
		for(int i=0; i<multipartFiles.length; i++) {
			
			if(!multipartFiles[i].isEmpty()) {
				int type = 1;
				int defaultNy = 1;
				String classOrName = shipDto.getClass().getSimpleName().toString().toLowerCase();
				String className = classOrName.substring(0, classOrName.length() - 3);
				System.out.println("Class Name: " + className);

				String fileName = multipartFiles[i].getOriginalFilename();
				System.out.println("Original File Name: " + fileName);
				
				System.out.println("File Type: " + type);
				
				String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
				System.out.println("File Extension: " + ext);

				String uuid = UUID.randomUUID().toString();
				System.out.println("UUID: " + uuid);

				String uuidFileName = uuid + "." + ext;
				System.out.println("UUID File Name: " + uuidFileName);

				String pathModule = className;
				System.out.println("Path Module: " + pathModule);

				String nowString = UtilDateTime.nowString();
				System.out.println("Current Date and Time: " + nowString);

				String pathDate = nowString.substring(0,4) + "/" + nowString.substring(5,7) + "/" + nowString.substring(8,10);
				System.out.println("Path Date: " + pathDate);

				String path = pathModule + "/" + type + "/" + pathDate + "/";
				System.out.println("Final Path: " + path);

		        ObjectMetadata metadata = new ObjectMetadata();
		        metadata.setContentLength(multipartFiles[i].getSize());
		        metadata.setContentType(multipartFiles[i].getContentType());
		        
		        amazonS3Client.putObject(bucket, path + uuidFileName, multipartFiles[i].getInputStream(), metadata);
				
		        String objectUrl = amazonS3Client.getUrl(bucket, path + uuidFileName).toString();
		        
				shipDto.setPath(objectUrl);
				shipDto.setOriginalName(fileName);
				shipDto.setUuid(uuidFileName);
				shipDto.setExt(ext);
				shipDto.setSize(multipartFiles[i].getSize());
				
//				shipDto.setTableName(tableName);
				shipDto.setType(type);
				shipDto.setDefaultNy(defaultNy);
				shipDto.setSort(maxNumber + i);
				System.out.println("getSeq "+i+"번: " + shipDto.getSpSeq());
				shipDto.setPseq(seq);
				
				shipDao.insertUploaded(shipDto);
			}
		}
		fishShipDto.setShip_spSeq(shipDto.getSpSeq());
		List<String> fishSeqs = fishShipDto.getFsSeqList();
		fishShipDto.setSpSeq(shipDto.getSpSeq());
		List<FishShipDto> fishLists	= fishShipDao.FishShipOneSelectList(fishShipDto);
		
		//추가
		int j = 0;
		for(String fishSeq:fishSeqs) {
			System.out.println("----------------------------------------------------");
			System.out.println("현재어종번호: "+fishSeq+"번");
			j++;
			fishShipDto.setShip_spSeq(shipDto.getSpSeq());
	        fishShipDto.setFish_fsSeq(fishSeq);
	        fishShipDto.setFsOrder(j);
			fishShipDao.update(fishShipDto);
			System.out.println("j: "+j);
			boolean isExist = false;
			
			for(FishShipDto fish: fishLists) {
				System.out.println("FishShipDto.seq: "+fish.getShip_spSeq());

				System.out.println("어종번호: "+fish.getFish_fsSeq());
				if(fish.getDelNy() ==0) {
					if(fishSeq.equals(fish.getFish_fsSeq())) {
						System.out.println("어종존재");
						isExist = true;
						break;
					}
					System.out.println("getDelNy:1");
				}else if(fish.getDelNy() ==1){
					if(fishSeq.equals(fish.getFish_fsSeq())) {
						System.out.println("어종존재 DelNy:1");
						isExist = true;
						fishShipDto.setShip_spSeq(shipDto.getSpSeq());
				        fishShipDto.setFish_fsSeq(fish.getFish_fsSeq());
				        fishShipDto.setFsOrder(j);
						fishShipDao.update(fishShipDto);
						break;
					}
					System.out.println("어종없음 DelNy:1");
				}

				System.out.println("어종없음");	

			}
			if (!isExist) {
				System.out.println("배번호: "+shipDto.getSpSeq());
				fishShipDto.setShip_spSeq(shipDto.getSpSeq());
		        fishShipDto.setFish_fsSeq(fishSeq);
		        fishShipDto.setFsOrder(j);
		        System.out.println(fishSeq + "번 어종 추가");
		        fishShipDao.ShipFishInsert(fishShipDto);
		    }
		}
		
		//삭제
		for (FishShipDto fish : fishLists) {
		    boolean isExist = false;

		    for (String fishSeq : fishSeqs) {
		        if (fish.getFish_fsSeq().equals(fishSeq)) {
		        	isExist = true;
		            break;
		        }
		    }

		    if (!isExist) {
		    	System.out.println("배번호: "+fishShipDto.getShip_spSeq());
		    	fishShipDto.setSpSeq(shipDto.getSpSeq());  
		    	fishShipDto.setFish_fsSeq(fish.getFish_fsSeq()); 

		        System.out.println(fish.getFish_fsSeq() + "번 어종 제외");
		        fishShipDao.uelete(fishShipDto);
		    }
		}
		return result;
	}
	public int uelete(ShipDto shipDto) {
		return shipDao.uelete(shipDto);
	}
	public int delete(ShipDto shipDto) {
		return shipDao.delete(shipDto);
	}
	public List<ShipDto> selectUsrList2(ShipVo vo){
		return shipDao.selectUsrList2(vo);
	}
	public List<ShipDto> selectUsrList3(ShipVo vo){
		return shipDao.selectUsrList3(vo);
	}

}
