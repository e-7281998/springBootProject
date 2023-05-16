package com.shinhan.education;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.shinhan.education.VO.FreeBoard;
import com.shinhan.education.VO.FreeBoardReply;
import com.shinhan.education.repository.FreeBoardRepository;
import com.shinhan.education.repository.FreeRepliesRepository;

//양방향 test
@SpringBootTest
public class OneToManyToOneTest {
	@Autowired
	FreeBoardRepository boardRepo;
	@Autowired
	FreeRepliesRepository replyRepo;

	//board번호가 100번 삭제... 댓글도 삭제된다.
	//@Test
	void test7() {
		boardRepo.deleteById(100L); 
	}
	
	//board번호가 100이상인 board 조회... paging 추가
	//@Test
	void test6() {
		//0번째 페이지에서, 페이지당 3건만 출력, 정렬은 desc로 하고, 정렬 기준은 bno
		//Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "bno");
		Pageable paging = PageRequest.of(0, 3, Sort.Direction.ASC, "bno");
		List<FreeBoard> blist = boardRepo.findByBnoGreaterThan(99L, paging); 
		blist.forEach(board -> {
			System.out.println(board.getTitle() + " ===> " + board.getReplies().size());
		});
	}
	
	//댓글 모두 조회(n:1이용) ... boardno가 15인 댓글조회
	//@Test
	void test5() {
		boardRepo.findById(15L).ifPresent(board -> {
			replyRepo.findByBoard(board).forEach(reply -> {
				System.out.println(reply + " ===> " + reply.getBoard().getBno());
			});
		});
	}
	
	//댓글 모두 조회(n:1이용)
	//@Test
	void test4() {
		replyRepo.findAll().forEach(re -> {
			System.out.println(re.getReply()+"===>"+re.getBoard().getWriter());
		});
	}
	
	//board 모두 조회(1:n이용)
	//@Test
	void test3() {
		boardRepo.findAll().forEach(board -> {
			//System.out.println(board);
			//board번호와 댓글개수
			System.out.println(board.getBno()+"===>"+board.getReplies().size()+"개");
		});
	}
	
	//15번 board에 댓글insert 10건 
	//@Test
	void test2() {
		Long[] arr = {1L, 10L, 50L, 100L};
		
		Arrays.stream(arr).forEach(index -> {
			boardRepo.findById(index).ifPresent(b -> {
				IntStream.rangeClosed(1, 10).forEach(i -> {
					FreeBoardReply reply = FreeBoardReply.builder()
														.reply("화요일"+i)
														.replyer("user"+(i%2))
														.board(b)
														.build();
					replyRepo.save(reply);
				});
			});
		}); 
	}
	
	//board insert 100건
	//@Test
	void test1() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			FreeBoard board = FreeBoard.builder()
									   .title("게시글제목"+i)
									   .writer("user"+(i%10))
									   .content("게시글....")
									   .build();
			boardRepo.save(board);
		});
	
	}
}
