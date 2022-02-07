package com.board.springboot;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/file")
@Controller
public class FileController {
	
	Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@PostMapping("/save")
	public void save(@RequestParam("uploadFile") MultipartFile multipartFile) {
		logger.info("multipartFile : {}");
		if(multipartFile == null || multipartFile.isEmpty()) {
			
		}
		
		String uploadFilePath = "C:/dev/file/";
		logger.info("uploadFilePath : {}", uploadFilePath);
		
		String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1, multipartFile.getOriginalFilename().length());
		String filename = UUID.randomUUID().toString() + "." + prefix;
		logger.info("filename : {}", filename);
		String pathname = uploadFilePath + filename;
		File dest = new File(pathname);
		if(!dest.isDirectory()) {
			dest.mkdir();
		}
		try {
			multipartFile.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
