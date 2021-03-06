package com.hy.springherb.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class FileuploadUtil {
	private static final Logger logger
		=LoggerFactory.getLogger(FileuploadUtil.class);
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	public List<Map<String, Object>> fileupload(HttpServletRequest request) 
			throws IllegalStateException, IOException {
		//파일업로드 처리
		MultipartHttpServletRequest multipartRequest 
			= (MultipartHttpServletRequest) request;
		
		Map<String, MultipartFile> fileMap
			=multipartRequest.getFileMap();
		
		//file정보 결과를 저장할 list
				List<Map<String, Object>> list
					=new ArrayList<Map<String,Object>>();
		
		Iterator<String> iter = fileMap.keySet().iterator();
		while(iter.hasNext()) {
			String key =iter.next();
			MultipartFile tempFile =fileMap.get(key);
			//=> 업로드된 파일을 임시파일 형태로 제공

			//업로드 된경우
			if(!tempFile.isEmpty()) {
				String ofileName=tempFile.getOriginalFilename();
				//unique한 파일명 구하기
				String fileName=getUniqueFileName(ofileName);
				
				long fileSize=tempFile.getSize();
				
				//업로드 처리
				String uploadPath= getUploadPath(request);
				
				File file = new File(uploadPath, fileName);
				tempFile.transferTo(file);
				
				//결과 저장
				Map<String , Object> resultMap = new HashMap<String, Object>();
				resultMap.put("originalFilename", ofileName);
				resultMap.put("filename", fileName);
				resultMap.put("filesize", fileSize);
				
				list.add(resultMap);
				
			}
		}//while	

		return list;
	}

	public String getUploadPath(HttpServletRequest request) {
		String upPath="";
		
		String type=fileProperties.getProperty("file.upload.type");
		if(type.equals("test")) {
			//테스트시 경로
			upPath=fileProperties.getProperty("file.upload.path.test");
			logger.info("test 경로:"+ upPath);
		}else {
			//배포시 경로
			upPath=fileProperties.getProperty("file.upload.path");
			logger.info("배포시 경로:"+ upPath);
			
			//실제 물리적인 경로 구하기
			upPath
			=request.getSession().getServletContext().getRealPath(upPath);
			logger.info("배포시 실제 물리적 경로:"+ upPath);
		}
		
		return upPath;
	}

	private String getUniqueFileName(String ofileName) {
		//test.txt => test20171204054830123.txt
		int idx = ofileName.lastIndexOf(".");
		String fName = ofileName.substring(0, idx);  //test
		String ext = ofileName.substring(idx);  //.txt
		
		String fileName = fName+getCurrentTime()+ext;
		logger.info("변경된 파일 이름:"+ fileName);
		
		return fileName;
	}
	
	private String getCurrentTime() {
		SimpleDateFormat sdf 
		= new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		Date d = new Date();
		
		return sdf.format(d);		
	}
	
}












