package com.shinhan.education;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.shinhan.education.VO.BoardVO;
import com.shinhan.education.repository.StudyBoardRepository;

@SpringBootTest
public class StudyBoardTest {

	@Autowired
	StudyBoardRepository brepo;
	
	@Test
	public void findByPaging() {
		//page마다 5건씩 나눈 것 중 1페이지
		Pageable pageable =  PageRequest.of(1, 5);
		brepo.findByPage(pageable).forEach(board -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void findTitle2() {
		List<BoardVO> blist = brepo.findByTitle2("5", "게시");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void findTitle() {
		List<BoardVO> blist = brepo.findByTitle("5", "게시");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void insertValue() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			BoardVO board = BoardVO.builder()
									.title("게시글"+i)
									.content("게시글"+i)
									.writer("작성자"+ (i%10+1))
									.build();
			brepo.save(board);
		});
	}
}
