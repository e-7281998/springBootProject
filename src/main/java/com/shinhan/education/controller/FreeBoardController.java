package com.shinhan.education.controller;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shinhan.education.repository.FreeBoardRepository;
import com.shinhan.education.repository.FreeRepliesRepository;

@Controller
public class FreeBoardController {
	
	@Autowired
	FreeBoardRepository boardRepo;
	
	@Autowired
	FreeRepliesRepository replyRepo;
	
	@GetMapping("/freeboard/eltest")
 	public void freeboard3(Model dataScope, HttpSession session) {
		dataScope.addAttribute("boardList", boardRepo.findAll());
		dataScope.addAttribute("now", new Date());
		dataScope.addAttribute("price", 123456789);
		dataScope.addAttribute("title", "This is a just sample");
		dataScope.addAttribute("options", Arrays.asList("apple","banana","kiwi"));
		
		session.setAttribute("userName", "이탬");
 	}
	
	@GetMapping("/freeboard/selectAll")
 	public String freeboard2(Model dataScope) {
		dataScope.addAttribute("boardList", boardRepo.findAll());
		return "freeboard/list";
	}
	
	@GetMapping("/freeboard/detail")
	//다음은 (@RequestParam("bon") Long bno, Model model)와 같음
	public void freeboard1(Long bno, Model model) {
		boardRepo.findById(bno).ifPresent(board -> {
			model.addAttribute("board", board);
		});
	}
	
	//다음과 같이 현재페이지가 아닐때 return하고싶지 않으면...
	//aa폴더 하위에 firstzone3.html이 존재해야 함
	@GetMapping("/aa/firstzone3")
	public void test3(Model model) {
 		model.addAttribute("greeting", "방가~");
		model.addAttribute("company", "신한DS");
 	}
	
	//2.요청 이름과 페이지 이름이 다른 경우
	//return시 String으로 경로(?)보냄
	@GetMapping("/firstzone2")
	public String test2(Model model) {
		//html에서 jstl사용하는것과 같이 ${company}로 작성하면 된다.
		model.addAttribute("greeting", "Hello~");
		model.addAttribute("company", "ShinHan");
		return "firstzone1";	//templates/firstzone1.html로 forward됨
	}
	
	//1.요청 이름과 페이지 이름이 같은 경우
	//return type이 void이므로 resources/templates/firstzone1.html파일이 있어야 한다.
	@GetMapping("/firstzone1")
	public void test1(Model model) {
		//html에서 jstl사용하는것과 같이 ${company}로 작성하면 된다.
		model.addAttribute("greeting", "하이~");
		model.addAttribute("company", "신한금융");
	}
}
