package com.shinhan.education.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo3.WebBoard;
import com.shinhan.education.vo3.WebReply;

@RestController
@RequestMapping("/replies")
public class WebReplyController {
	
	@Autowired
	WebReplyRepository replyRepo;
	
	@GetMapping("/{bno}")
	public List<WebReply> selectAllReply(@PathVariable("bno") Long bno) {
		System.out.println(bno+"보드의 모든 댓글조회");
		WebBoard board = new WebBoard();
		board.setBno(bno);
		List<WebReply> replies = replyRepo.findByBoard(board);
		return replies;
	}
}
