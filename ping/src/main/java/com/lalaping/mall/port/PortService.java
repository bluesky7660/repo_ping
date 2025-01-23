package com.lalaping.mall.port;

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
public class PortService {
	@Autowired
	public PortDao portDao;
	
	@Autowired
	S3Config s3Config;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	public List<PortDto> selectList(PortVo vo) {
		return portDao.selectList(vo);
	}
	public List<PortDto> allList() {
		return portDao.allList();
	}
	public int insert(PortDto portDto) throws Exception {
		int result = portDao.insert(portDto);
		MultipartFile[] multipartFiles = portDto.getUploadFiles();
		int maxNumber = multipartFiles.length;
		AmazonS3Client amazonS3Client = s3Config.amazonS3Client();
		String seq = portDto.getPtSeq();
		for(int i=0; i<multipartFiles.length; i++) {
			
			if(!multipartFiles[i].isEmpty()) {
				int type = 1;
				int defaultNy = 1;
				String classOrName = portDto.getClass().getSimpleName().toString().toLowerCase();
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
		        
				portDto.setPath(objectUrl);
				portDto.setOriginalName(fileName);
				portDto.setUuid(uuidFileName);
				portDto.setExt(ext);
				portDto.setSize(multipartFiles[i].getSize());
				
//				portDto.setTableName(tableName);
				portDto.setType(type);
				portDto.setDefaultNy(defaultNy);
				portDto.setSort(maxNumber + i);
				System.out.println("getSeq "+i+"번: " + portDto.getPtSeq());
				portDto.setPseq(seq);
				
				portDao.insertUploaded(portDto);
			}
		}
		return result;
	}
	public PortDto selectOne(PortDto portDto) {
		return portDao.selectOne(portDto);
	}
	public int update(PortDto portDto) throws Exception {
		int result = portDao.update(portDto);
		MultipartFile[] multipartFiles = portDto.getUploadFiles();
		int maxNumber = multipartFiles.length;
		AmazonS3Client amazonS3Client = s3Config.amazonS3Client();
		String seq = portDto.getPtSeq();
		for(int i=0; i<multipartFiles.length; i++) {
			
			if(!multipartFiles[i].isEmpty()) {
				int type = 1;
				int defaultNy = 1;
				String classOrName = portDto.getClass().getSimpleName().toString().toLowerCase();
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
		        
				portDto.setPath(objectUrl);
				portDto.setOriginalName(fileName);
				portDto.setUuid(uuidFileName);
				portDto.setExt(ext);
				portDto.setSize(multipartFiles[i].getSize());
				
//				portDto.setTableName(tableName);
				portDto.setType(type);
				portDto.setDefaultNy(defaultNy);
				portDto.setSort(maxNumber + i);
				System.out.println("getSeq "+i+"번: " + portDto.getPtSeq());
				portDto.setPseq(seq);
				
				portDao.insertUploaded(portDto);
			}
		}
		return result;
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
	public int listCount(PortVo vo) {
		return portDao.listCount(vo);
	}

}
