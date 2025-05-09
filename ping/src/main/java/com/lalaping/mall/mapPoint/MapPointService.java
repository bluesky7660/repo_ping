package com.lalaping.mall.mapPoint;

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
import com.lalaping.mall.fishMappoint.FishMappointDao;
import com.lalaping.mall.fishMappoint.FishMappointDto;
import com.lalaping.mall.fishMappoint.FishMappointService;

@Service
public class MapPointService {
	@Autowired
	public MapPointDao mapPointDao;
	
	@Autowired
	S3Config s3Config;
	@Autowired
	FishMappointDao fishMappointDao;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	public List<MapPointDto> selectList(MapPointVo vo) {
		return mapPointDao.selectList(vo);
	}
	public List<MapPointDto> selectUsrList(MapPointVo vo) {
		return mapPointDao.selectUsrList(vo);
	}
	public List<MapPointDto> selectSearchList(MapPointVo vo) {
		return mapPointDao.selectSearchList(vo);
	}
	public List<MapPointDto> allList(MapPointVo vo) {
		return mapPointDao.allList(vo);
	}
	public List<MapPointDto> nearList(MapPointVo vo) {
		return mapPointDao.nearList(vo);
	}
	public List<MapPointDto> portNearList(MapPointVo vo) {
		return mapPointDao.portNearList(vo);
	}
	public List<MapPointDto> sessSelectList(MapPointVo vo) {
		return mapPointDao.sessSelectList(vo);
	}
	public int portNearCount(MapPointVo mapPointVo) {
		return mapPointDao.portNearCount(mapPointVo);
	}
	public int nearCount(MapPointVo mapPointVo) {
		return mapPointDao.nearCount(mapPointVo);
	}
	public int sessSelectCount(MapPointVo mapPointVo) {
		return mapPointDao.sessSelectCount(mapPointVo);
	}
	public int selectUsrCount(MapPointVo mapPointVo) {
		return mapPointDao.selectUsrCount(mapPointVo);
	}
	public int insert(MapPointDto mapPointDto, FishMappointDto fishMappointDto) throws Exception{
		int a = mapPointDao.insert(mapPointDto);
		System.out.println("multipartFiles:"+mapPointDto.getUploadFiles());
		MultipartFile[] multipartFiles = mapPointDto.getUploadFiles();
		int maxNumber = multipartFiles.length;
		AmazonS3Client amazonS3Client = s3Config.amazonS3Client();
		String seq = mapPointDto.getMpSeq();
		for(int i=0; i<multipartFiles.length; i++) {
			
			if(!multipartFiles[i].isEmpty()) {
				int type = 1;
				int defaultNy = 1;
				String classOrName = mapPointDto.getClass().getSimpleName().toString().toLowerCase();
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
		        
				mapPointDto.setPath(objectUrl);
				mapPointDto.setOriginalName(fileName);
				mapPointDto.setUuid(uuidFileName);
				mapPointDto.setExt(ext);
				mapPointDto.setSize(multipartFiles[i].getSize());
				
//				mapPointDto.setTableName(tableName);
				mapPointDto.setType(type);
				mapPointDto.setDefaultNy(defaultNy);
				mapPointDto.setSort(maxNumber + i);
				System.out.println("getSeq "+i+"번: " + mapPointDto.getMpSeq());
				mapPointDto.setPseq(seq);
				
				mapPointDao.insertUploaded(mapPointDto);
			}
		}
		
		fishMappointDto.setMapPoint_mpSeq(mapPointDto.getMpSeq());
		int i=0;
		for(String fsSeq :fishMappointDto.getFsSeqList()) {
			i=+1;
			System.out.println("fsOrder:"+i);
			System.out.println("fish_fsSeq[getFsSeqList]:"+fsSeq);
			System.out.println("delNy:"+0);
			System.out.println("mapPoint_mpSeq:"+fishMappointDto.getMapPoint_mpSeq());
			fishMappointDto.setFsOrder(i);
			fishMappointDto.setFish_fsSeq(fsSeq);
			fishMappointDao.mappointFishInsert(fishMappointDto);
		}
		
		return a;
	}
	public MapPointDto selectOne(MapPointDto mapPointDto) {
		return mapPointDao.selectOne(mapPointDto);
	}
	public MapPointDto lastPoint() {
		return mapPointDao.lastPoint();
	}
	public MapPointDto selectUsrOne(MapPointDto mapPointDto) {
		return mapPointDao.selectUsrOne(mapPointDto);
	}
	public int update(MapPointDto mapPointDto, FishMappointDto fishMappointDto) throws Exception{
		int result =mapPointDao.update(mapPointDto);
		MultipartFile[] multipartFiles = mapPointDto.getUploadFiles();
		int maxNumber = multipartFiles.length;
		AmazonS3Client amazonS3Client = s3Config.amazonS3Client();
		String seq = mapPointDto.getMpSeq();
		for(int i=0; i<multipartFiles.length; i++) {
			
			if(!multipartFiles[i].isEmpty()) {
				int type = 1;
				int defaultNy = 1;
				String classOrName = mapPointDto.getClass().getSimpleName().toString().toLowerCase();
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

//				String pathForView = Constants.UPLOADED_PATH_PREFIX_FOR_VIEW_LOCAL + "/" + pathModule + "/" + type + "/" + pathDate + "/";
				
				
		        ObjectMetadata metadata = new ObjectMetadata();
		        metadata.setContentLength(multipartFiles[i].getSize());
		        metadata.setContentType(multipartFiles[i].getContentType());
		        
		        amazonS3Client.putObject(bucket, path + uuidFileName, multipartFiles[i].getInputStream(), metadata);
				
		        String objectUrl = amazonS3Client.getUrl(bucket, path + uuidFileName).toString();
		        
				mapPointDto.setPath(objectUrl);
				mapPointDto.setOriginalName(fileName);
				mapPointDto.setUuid(uuidFileName);
				mapPointDto.setExt(ext);
				mapPointDto.setSize(multipartFiles[i].getSize());
				
//				mapPointDto.setTableName(tableName);
				mapPointDto.setType(type);
				mapPointDto.setDefaultNy(defaultNy);
				mapPointDto.setSort(maxNumber + i);
				System.out.println("getSeq "+i+"번: " + mapPointDto.getMpSeq());
				mapPointDto.setPseq(seq);
				
				mapPointDao.insertUploaded(mapPointDto);
			}
		}
		fishMappointDto.setMapPoint_mpSeq(mapPointDto.getMpSeq());
		List<String> mpSeqs = fishMappointDto.getFsSeqList();
		fishMappointDto.setMpSeq(mapPointDto.getMpSeq());
		List<FishMappointDto> mpLists	= fishMappointDao.FishMapOneSelectList(fishMappointDto);
		
		//추가
		int j = 0;
		for(String mpSeq:mpSeqs) {
			j++;
			fishMappointDto.setMapPoint_mpSeq(mapPointDto.getMpSeq());
	        fishMappointDto.setFish_fsSeq(mpSeq);
	        fishMappointDto.setFsOrder(j);
			fishMappointDao.update(fishMappointDto);
			boolean isExist = false;
			
			for(FishMappointDto mapPoint: mpLists) {
				if(mapPoint.getDelNy() ==0) {
					if(mpSeq.equals(mapPoint.getFish_fsSeq())) {
						isExist = true;
						break;
					}
				}else if(mapPoint.getDelNy() ==1){
					if(mpSeq.equals(mapPoint.getFish_fsSeq())) {
						isExist = true;
						fishMappointDto.setMapPoint_mpSeq(mapPointDto.getMpSeq());
				        fishMappointDto.setFish_fsSeq(mapPoint.getFish_fsSeq());
				        fishMappointDto.setFsOrder(j);
						fishMappointDao.update(fishMappointDto);
						break;
					}
				}
			}
			if (!isExist) {
				fishMappointDto.setMapPoint_mpSeq(mapPointDto.getMpSeq());
		        fishMappointDto.setFish_fsSeq(mpSeq);
		        fishMappointDto.setFsOrder(j);
		        fishMappointDao.mappointFishInsert(fishMappointDto);
		    }
		}
		
		//삭제
		for (FishMappointDto mapPoint : mpLists) {
		    boolean isExist = false;

		    for (String mpSeq : mpSeqs) {
		        if (mapPoint.getFish_fsSeq().equals(mpSeq)) {
		        	isExist = true;
		            break;
		        }
		    }

		    if (!isExist) {
		    	fishMappointDto.setMpSeq(mapPointDto.getMpSeq());  
		    	fishMappointDto.setFish_fsSeq(mapPoint.getFish_fsSeq()); 
		        fishMappointDao.uelete(fishMappointDto);
		    }
		}
		return result;
	}
	public int uelete(MapPointDto mapPointDto) {
		return mapPointDao.uelete(mapPointDto);
	}
	public int delete(MapPointDto mapPointDto) {
		return mapPointDao.delete(mapPointDto);
	}
	public int listCount(MapPointVo vo) {
		return mapPointDao.listCount(vo);
	}

}
