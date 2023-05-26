package com.shinhan.education.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.querydsl.core.types.Predicate;
import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo3.PageMarker;
import com.shinhan.education.vo3.PageVO;
import com.shinhan.education.vo3.WebBoard;

import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import sun.font.AttributeValues;

@Log
@RestController
@RequestMapping("/rest/webboard")
public class WebBoardRestController {
	
	@Autowired
	WebBoardRepository boardRepo;
	
	@Autowired
	WebReplyRepository replyRepo; 
	@ApiOperation(value="게시판 등록화면", notes="게시판 등록화면!!")
	//새로은 정보 등록
	@PostMapping(value = "/register.do", consumes = "application/json")	//data가 json으로 들어온다.
	public WebBoard registerPost(@RequestBody WebBoard board) {
		WebBoard newBoard = boardRepo.save(board); 
		return  newBoard;
	}
	
	//등록 페이지로만 가니까 아무런 행동, 파라미터 필요 없음
	@GetMapping("/register.do")
	public void registerGet() { 
	}
	
	
	//삭제하기
	@DeleteMapping(value = "/delete.do/{bno}", produces = "text/plain;charset=utf-8")
	public String deletePost(@PathVariable Long bno ) {   
		boardRepo.deleteById(bno); 
		return "삭제작업 완료 - delete";
	}
	
	//수정하기
	@PutMapping(value = "/modify.do", consumes = "application/json")
	public WebBoard updatePost(@RequestBody WebBoard board) { 
		WebBoard savedBoard = boardRepo.save(board); 
		return savedBoard;
	}
	
	//수정하기
	@GetMapping("/modify.do/{bno}")
	public WebBoard updateOrDelete(@PathVariable Long bno ) { 
		return boardRepo.findById(bno).orElse(null);
 	}
	
	//상세보기
	@GetMapping("/view.do/{bno}")
	public WebBoard selectById(@PathVariable Long bno ) {
		return boardRepo.findById(bno).orElse(null);
 
	} 
	
 	@GetMapping("/list.do")
	public List<WebBoard> selectAll(PageVO pageVO, Model model) {
		 
		if(pageVO == null) {
			pageVO = new PageVO();
			pageVO.setPage(1);
		} 
		
		Predicate pre = boardRepo.makePredicate(pageVO.getType(), pageVO.getKeyword()); 
		Pageable paging = pageVO.makePageable(0, "bno"); 
		Page<WebBoard> result = boardRepo.findAll(pre, paging);
		
		//방법1
		//List<WebBoard> blist = result.getContent(); 
		//System.out.println("전체 페이지수 : " + result.getTotalPages());
		//System.out.println("전체 건수 : " + result.getTotalElements());
		
		PageMarker<WebBoard> pageMaker = new PageMarker<>(result, pageVO.getSize()); 
		model.addAttribute("blist" , pageMaker);
		
		//방법2
		//Page<WebBoard> p_result = pageMaker.getResult();
		//System.out.println(p_result.getContent());
		
		return result.getContent();
	}
}
