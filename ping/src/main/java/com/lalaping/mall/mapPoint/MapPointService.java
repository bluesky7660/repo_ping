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


@Service
public class MapPointService {
	@Autowired
	public MapPointDao mapPointDao;
	
	@Autowired
	S3Config s3Config;
	
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
	public int insert(MapPointDto mapPointDto) {
		return mapPointDao.insert(mapPointDto);
	}
	public MapPointDto selectOne(MapPointDto mapPointDto) {
		return mapPointDao.selectOne(mapPointDto);
	}
	public MapPointDto selectUsrOne(MapPointDto mapPointDto) {
		return mapPointDao.selectUsrOne(mapPointDto);
	}
	public int update(MapPointDto mapPointDto) throws Exception{
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
		return result;
	}
	public int uelete(MapPointDto mapPointDto) {
		return mapPointDao.uelete(mapPointDto);
	}
	public int delete(MapPointDto mapPointDto) {
		return mapPointDao.delete(mapPointDto);
	}

}
