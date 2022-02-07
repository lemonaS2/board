package com.board.springboot.user.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequestMapping("/upload")
@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@RequestMapping("/uploadForm")
	public String uploadForm() {
		return "/upload/uploadForm";
	}
	
	@ResponseBody
	@PostMapping("/uploadFile")
	public String uploadFile(MultipartHttpServletRequest request) throws Exception{
		
		String path = "C:\\dev\\file";
		File file = new File(path);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		Iterator<String> iterator = request.getFileNames();
		while(iterator.hasNext()) {
			List<MultipartFile> list = request.getFiles(iterator.next());
			for(MultipartFile multipartFile : list) {
				
//				String newFileName = "temp";
				
				file = new File(path + "/" + multipartFile.getOriginalFilename());
				multipartFile.transferTo(file);
			}
			
		}
		
		
		return "1";
	}
	
}
