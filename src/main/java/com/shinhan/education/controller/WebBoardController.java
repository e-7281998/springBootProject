package com.shinhan.education.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.querydsl.core.types.Predicate;
import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo3.PageMarker;
import com.shinhan.education.vo3.PageVO;
import com.shinhan.education.vo3.WebBoard;

import lombok.extern.java.Log;
import sun.font.AttributeValues;

@Log
@Controller
@RequestMapping("/webboard")
public class WebBoardController {
	
	@Autowired
	WebBoardRepository boardRepo;
	
	@Autowired
	WebReplyRepository replyRepo; 
	
	//새로은 정보 등록
	@PostMapping("/register.do")
	public String registerPost(WebBoard board, RedirectAttributes attr) {
		WebBoard newBoard = boardRepo.save(board);
		if(newBoard != null) {
			attr.addFlashAttribute("msg", "insert OK");
		}else {
			attr.addFlashAttribute("msg", "insert Fail");
		}
		return "redirect:list.do";
	}
	
	//등록 페이지로만 가니까 아무런 행동, 파라미터 필요 없음
	@GetMapping("/register.do")
	public void registerGet() { 
	}
	
	
	//삭제하기
	@PostMapping("/delete.do")
	public String deletePost(Long bno, PageVO pageVO,
			RedirectAttributes attr) {  
		//boardRepo.deleteById(board.getBno());
		boardRepo.deleteById(bno);
		//addFlashAttribute : 새로고침하면 없어짐 (1회성)
		attr.addFlashAttribute("msg", "delete OK");
		//addAttribute : 새로고침해도 유지
		attr.addAttribute("page", pageVO.getPage());
		attr.addAttribute("size", pageVO.getSize());
		attr.addAttribute("keyword", pageVO.getKeyword());
		attr.addAttribute("type", pageVO.getType());
		return "redirect:list.do";
	}
	
	//수정하기
	@PostMapping("/modify.do")
	public String updatePost(WebBoard board, PageVO pageVO, RedirectAttributes attr) { 
		WebBoard savedBoard = boardRepo.save(board);
		
		if(savedBoard == null) {
			attr.addFlashAttribute("msg", "Update fail");
		}else {
			attr.addFlashAttribute("msg", "Update success");
		}
		
		attr.addAttribute("bno", board.getBno());
		attr.addAttribute("page", pageVO.getPage());
		attr.addAttribute("size", pageVO.getSize());
		attr.addAttribute("keyword", pageVO.getKeyword());
		attr.addAttribute("type", pageVO.getType());
		
		return "redirect:view.do";
	}
	
	//수정하기
	@GetMapping("/modify.do")
	public void updateOrDelete(Long bno, Model model, PageVO pageVO) { 
		boardRepo.findById(bno).ifPresent(board -> {
			model.addAttribute("board", board);
			model.addAttribute("pageVO", pageVO);
		});
	}
	
	//상세보기
	@GetMapping("/view.do")
	public void selectById(Long bno, Model model, PageVO pageVO) {
		boardRepo.findById(bno).ifPresent(board -> {
			model.addAttribute("board", board);
			model.addAttribute("pageVO", pageVO);
		});
	} 
	
	//페이지 만들때 .do는 무시해도 됨
	@GetMapping("/list.do")
	public void selectAll(PageVO pageVO, Model model) {
		//title like %
		//makePredicate(타입, 원하는거);=
		//Predicate pre = boardRepo.makePredicate("title", "%");
		if(pageVO == null) {
			pageVO = new PageVO();
			pageVO.setPage(1);
		}
		
		
		Predicate pre = boardRepo.makePredicate(pageVO.getType(), pageVO.getKeyword());
		
		Pageable paging = pageVO.makePageable(0, "bno");
		
		//0페이지인데 한 페이지에 10개씩 보여줘, 정렬은 bno기준 desc으로
		//Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
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
	}
}
