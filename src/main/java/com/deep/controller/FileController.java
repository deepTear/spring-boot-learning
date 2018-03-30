package com.deep.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.deep.service.api.FileService;

@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;
	
	@RequestMapping("/select")
	public String fileSelect(){
		
		return "file-select";
	}
	
	@RequestMapping("/upload")
	public String fileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request){
		fileService.fileUpload(file);
		return "redirect:/file/select";
	}
}
