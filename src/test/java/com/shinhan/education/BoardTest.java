package com.shinhan.education;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.shinhan.education.VO.BoardVO;
import com.shinhan.education.VO.CarVO;
import com.shinhan.education.repository.BoardRepository;

import lombok.extern.java.Log;

//JUNIT으로 단위 test하기 
@SpringBootTest
@Log
class BoardTest {

	Logger logger = LoggerFactory.getLogger(BoardTest.class);
	
	@Autowired
	BoardRepository brepo; 
	
	@Test
	void sample5() {	//잘 안됨
		Sort sort = Sort.by(Sort.Direction.DESC, new String[] {"writer", "bno"});
		Pageable paging = PageRequest.of(0, 5, sort);	//(몇 페이지인지, 한페이지의 사이즈)
 		
		Page<BoardVO> result = brepo.findByBnoGreaterThan(50L, paging);
		log.info("페이지당 건수 : " + result.getSize());
		log.info("페이지당 총수 : " + result.getTotalPages());
		log.info("전체 건수 : " + result.getTotalElements() );
		log.info("다음 페이지 정보 : " + result.nextPageable() );
		List<BoardVO> blist = result.getContent();
		blist.forEach(board -> {
			log.info(board.toString());
		}); 
	}
	
	//@Test
	void sample4() {	//??잘 안됨
		//Sort sort = Sort.by("bno").descending();
		
		//order by writer desc, bno desc
		Sort sort = Sort.by(Sort.Direction.DESC, new String[] {"writer", "bno"});
		Pageable paging = PageRequest.of(0, 5, sort);	//(몇 페이지인지, 한페이지의 사이즈)
 		
		List<BoardVO> blist = brepo.findByTitleContaining("제목", paging);
		blist.forEach(board -> {
			log.info(board.toString());
		}); 
	}
	
	//@Test
	void sample3() {	//??잘 안됨
		Pageable paging = PageRequest.of(2, 5);	//(몇 페이지인지, 한페이지의 사이즈)
 		List<BoardVO> blist = brepo.findByTitleContainingOrderByTitleDesc("제목", paging);
		blist.forEach(board -> {
			log.info(board.toString());
		}); 
	}
	
	//@Test
	void sample2() {
		
		List<BoardVO> blist = brepo.findByTitleContainingOrderByTitleDesc("제목");
		blist.forEach(board -> {
			log.info(board.toString());
		}); 
	}
	
	//@Test
	void sample1() {
		long rowCount = brepo.count();
		log.info(rowCount + "건");
		
		boolean result = brepo.existsById(10L);
		log.info(result ? "존재한다" : "존재하지않는다");
	}
	
	//@Test
	public void retrieve13() {	//content가 null인것 중에 짝수는 ABC, 홀수는 abc
		List<BoardVO> blist = brepo.findByContentIgnoreCase("abc");
		blist.forEach(board -> {
			 System.out.println(board);
		}); 
	}
	
	//@Test
	public void retrieve12() {	//content가 null인것 중에 짝수는 ABC, 홀수는 abc
		List<BoardVO> blist = brepo.findByContentIsNull();
		blist.forEach(board -> {
			if(board.getBno() %2 == 0)
				board.setContent("ABC");
			else
				board.setContent("abc");
			brepo.save(board);
		}); 
	}
	
	//@Test
	public void retrieve11() {
		List<BoardVO> blist = brepo.findByContentIsNull();
		blist.forEach(board -> {
			System.out.println(board); 
		}); 
	}
	
	//@Test
	public void retrieve10() {
		List<BoardVO> blist = brepo.findByBnoBetweenOrderByBnoDesc(30L, 37L);
		blist.forEach(board -> {
			System.out.println(board);
			board.setContent(null);
			brepo.save(board);
		}); 
	}
	
	//@Test
	public void retrieve9() {	//notLike 값을 1로만 주면 %가 붙지 않는다. 그래서 1이 포함되도 출력.
								//다음과 같이 내가 %를 주어야 1이 포함되지 않은 것을 찾음
		List<BoardVO> blist = brepo.findByTitleNotLike("%1%");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void retrieve8() {	//findByTitleContainingAndBnoGreaterThan : title과 bno로 잧
		List<BoardVO> blist = brepo.findByTitleContainingAndBnoGreaterThan("1", 50L);
		blist.forEach(board -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void retrieve7() {	//findByWriterAndTitle : WriterAndTitle로 잧
		List<BoardVO> blist = brepo.findByTitleEndingWith("1");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}

	//@Test
	public void retrieve6() {	//findByWriterAndTitle : WriterAndTitle로 잧
		List<BoardVO> blist = brepo.findByTitleStartingWith("게시");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void retrieve5() {	//findByWriterAndTitle : WriterAndTitle로 잧
		List<BoardVO> blist = brepo.findByTitleContaining("8");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void retrieve4() {	//findByWriterAndTitle : WriterAndTitle로 잧기
		List<BoardVO> blist = brepo.findByWriterAndTitle("작성자9","게시글제목9");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}
		
	//@Test
	public void retrieve3() {	//findByWriter : Write로 잧기
		List<BoardVO> blist = brepo.findByWriter("작성자5");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void retrieve2() {	//findByTitle : title로 잧기
		List<BoardVO> blist = brepo.findByContent("게시글내용...21");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void retrieve1() {	//findByTitle : title로 잧기
		List<BoardVO> blist = brepo.findByTitle("제목수정");
		blist.forEach(board -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void test6() {	 //정보 수정
		BoardVO board = brepo.findById(20L).orElse(null);
		if(board != null) {	//200번이 있다면 100번의 정보 수정
			board.setContent("내용수정");
			board.setTitle("제목수정");
			board.setWriter("admin");
			brepo.save(board);	//이미 있는 데이터는 update됨. 
		}
	}
	
	//@Test
	public void test5() {	//특정아이디만 조회
		//id를 Long타입으로 주었기때문에 L붙임. id가 100번이 있으면(ifPresent) 화면에 출력
		brepo.findById(10L).ifPresent(board -> {	
			System.out.println("10번정보 " + board);
		});
		
		//20번이 있으면 정보 출력, 없다면 null
		BoardVO board = brepo.findById(200L).orElse(null);
		System.out.println("200번정보 " + board);
		
	}
	
	//@Test
	public void test4() {	//전부 조회
		brepo.findAll().forEach(board -> {
			System.out.println(board);
		});
	}
	
	//@Test
	public void test3() {	//테이블에 값 삽입하기
		
		//1부터 100까지 
		IntStream.range(1, 100).forEach(i -> {
			BoardVO board = BoardVO.builder()
								  .title("게시글제목" + i)
								  .content("게시글내용..." + i)
								  .writer("작성자"+(i%10))	//작성자는 10명만 있다고 하려고 i%10함
								  .build();

			//.sava() : DB에 값 저장할 때
				brepo.save(board);	//sava함수에 insert된다.
			});
		
	}
	
	//@Test
	void test2() {
		//@Builder 때문에 .builder 가능
		//CarVO car1 = CarVO.builder().build();	//noArg로 빌더함
		CarVO car1 = CarVO.builder()
				.model("A모델")
				.price(1000)
				.build();
		logger.info(car1.toString());
		
	}
	
	//@Test
	void test1() {
		CarVO car1 = new CarVO();
		car1.setModel("A모델");
		car1.setPrice(1000);
 		
		CarVO car2 = new CarVO();
		car2.setModel("A모델");
		car2.setPrice(1000);
		
		logger.info(car1.equals(car2)+"");
 	}
	
	//@Test
	void contextLoads() {
	}

}
