package com.lalith.file.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lalith.file.service.FileReaderService;

@RestController
public class FileReaderController {

	public static final Logger logger = LoggerFactory.getLogger(FileReaderController.class);

	@Autowired
	FileReaderService fileReader;

	@PostMapping("/file")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("path")String path) {
		logger.info("Inside File Reader Controller upload file Start");
		if (file.isEmpty()) {
			logger.error("please select a file to upload");
		}
		List<MultipartFile> multipartFile = new ArrayList<>();
		multipartFile.add(file);
		fileReader.saveFile(multipartFile,path);
		return ResponseEntity.ok("Successfully uploaded " + file.getOriginalFilename());
	}

	@PostMapping("/multiplefiles")
	public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files,@RequestParam("path")String path) {
		logger.info("Inside File Reader Controller uploadMultiplefiles Start");
		if (CollectionUtils.isEmpty(files)) {
			logger.error("please select a file to upload");
		}
		fileReader.saveFile(files,path);
		return ResponseEntity.ok("Successfully uploaded ");
	}

}
