package com.lalith.file.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lalith.file.common.CommonUtility;
import com.lalith.file.service.FileReaderService;
import com.opencsv.CSVReader;

@Service
public class FileReaderServiceImpl implements FileReaderService {

	public static final Logger logger = LoggerFactory.getLogger(FileReaderServiceImpl.class);
	
	
	@Override
	public void saveFile(List<MultipartFile> files,String path) {
		logger.info("Inside File Reader Service Impl save file");
		files.stream().forEach(file->{
			if(!file.isEmpty()){
				try {
					CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
					Integer columns = 0 ;
					List<String[]> allLines = reader.readAll();
					if(!CollectionUtils.isEmpty(allLines)){
						columns = Integer.valueOf(allLines.get(0).length);
						String location = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
						CommonUtility.createPdfFile(columns, allLines,location,path);
					}
					reader.close();
					}
				 catch (IOException e) {
						logger.error("CSV reader creates exception"+e);
				}
			}
		});;
	}

}
