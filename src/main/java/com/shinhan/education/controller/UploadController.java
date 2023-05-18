package com.shinhan.education.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.education.UploadFileUtils;

@Controller
public class UploadController {
	//application.properties에 설정된 경로 가져옴
	@Value("${spring.servlet.multipart.location}")
	String uploadPath ;
	
	@GetMapping("/shop/uploadTest")
	public void upload() {
		
	}
	
	@PostMapping("/shop/register")
	public String register(@RequestParam MultipartFile[] files, HttpServletRequest req) throws IOException, Exception {
		String imgUploadPath = uploadPath + File.separator + "upload";
		
		//방법1. upload폴더 생성하기
		/*
		File fi = new File(imgUploadPath);
		if(!fi.exists()) {
			fi.mkdir();
		}*/
		
		//폴더생성
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName= null;
		for(MultipartFile f:files) {
			String originFileName = f.getOriginalFilename();
			System.out.println("originFileName:" + originFileName);
			if(originFileName!=null && !originFileName.equals("")) {
				fileName = UploadFileUtils.fileUpload(imgUploadPath, originFileName,
						f.getBytes(), ymdPath);
				
			}else {
				fileName = File.separator + "images" + File.separator + "hide.png";
			}
			System.out.println(fileName);
		}
		return "redirect:uploadTest";
	}
}