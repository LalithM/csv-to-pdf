package com.lalith.file.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileReaderService {
	
	public void saveFile(List<MultipartFile> files,String path);

}
