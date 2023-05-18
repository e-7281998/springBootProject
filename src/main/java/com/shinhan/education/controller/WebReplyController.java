package com.shinhan.education.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo3.WebBoard;
import com.shinhan.education.vo3.WebReply;

@RestController
@RequestMapping("/replies")
public class WebReplyController {
	
	@Autowired
	WebReplyRepository replyRepo;
	
	@Autowired
	WebBoardRepository boardRepo;
	
	//ResponseEntity : 상태값도 같이 보내고 싶다면
	private ResponseEntity<List<WebReply>> makeReturn(Long bno, HttpStatus status){
		WebBoard board = WebBoard.builder().bno(bno).title("").build();
 		List<WebReply> replies = replyRepo.findByBoardOrderByRnoDesc(board);
		
		return new ResponseEntity<List<WebReply>>(replies, status);
	}
	
	//댓글 삭제
	@DeleteMapping("/{bno}/{rno}")
	public ResponseEntity<List<WebReply>> deleteReply(@PathVariable("bno") Long bno, @PathVariable("rno") Long rno){
		replyRepo.deleteById(rno);
//		WebBoard board = boardRepo.findById(bno).get();
//		List<WebReply> replies = replyRepo.findByBoardOrderByRnoDesc(board);
		
//		return new ResponseEntity<List<WebReply>>(replies, HttpStatus.OK);
		return makeReturn(bno, HttpStatus.OK);
	}
	
	//댓글 수정
	@PutMapping("/{bno}")
	public List<WebReply> updateReply(@RequestBody WebReply reply,@PathVariable Long bno){
		WebBoard board = boardRepo.findById(bno).get();
		reply.setBoard(board);
		replyRepo.save(reply);
		
		List<WebReply> replies = replyRepo.findByBoardOrderByRnoDesc(board);
		return replies;
	}
	
	//댓글 달기
	@PostMapping("/{bno}")
	public ResponseEntity<List<WebReply>> insertReply(@RequestBody WebReply reply,@PathVariable Long bno){
		WebBoard board = boardRepo.findById(bno).get();
		reply.setBoard(board);
		replyRepo.save(reply);
		
		List<WebReply> replies = replyRepo.findByBoardOrderByRnoDesc(board);
		return makeReturn(bno, HttpStatus.CREATED); 
		//return replies;
	}
	
	@GetMapping("/{bno}")
	public ResponseEntity<List<WebReply>> selectAllReply(@PathVariable("bno") Long bno) {
//		System.out.println(bno+"보드의 모든 댓글조회");
//		WebBoard board = new WebBoard();
//		board.setBno(bno);
//		List<WebReply> replies = replyRepo.findByBoardOrderByRnoDesc(board);
		
		return makeReturn(bno, HttpStatus.CREATED); 
//return replies;
	}
}
