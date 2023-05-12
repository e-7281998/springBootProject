package com.shinhan.education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.persistence.FetchType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.VO.PDSBoard;
import com.shinhan.education.VO.PDSFile;
import com.shinhan.education.repository.PDSBoardRepository;
import com.shinhan.education.repository.PDSFileRepository;

import lombok.extern.java.Log;
@Log
@SpringBootTest
public class OneToManyTest {
	
	@Autowired
	PDSFileRepository fileRepo;
	
	@Autowired
	PDSBoardRepository boardRepo;
	 
	//다음은 board별 file의 건수 출력 : fetch = FetchType.EAGER일 때임...
	@Test
	void getFileCount() {
		//Eager인 경우 
		boardRepo.findAll().forEach(board -> {
			System.out.println(board.getPname() + " --> " + board.getFiles2().size());
		});
		
		//lazy인 경우
		//LAZYf라면 PDSBoardRepository에 작성된 쿼리로 사용해야 함
		List<Object[]> blist = boardRepo.getFilesInfo2();
		blist.forEach(arr -> {
			System.out.println(Arrays.toString(arr));
		});
	}
	
	//@Test
	void test3() {
		//fetch = FetchType.LAZY 라면 다음과 같이 
		boardRepo.getFilesInfo(116).forEach(arr -> {
			System.out.println(Arrays.toString(arr));
		});
		
		//fetch = FetchType.EAGER 라면 다음과 같이  
		System.out.println("==========================");
		PDSBoard board = boardRepo.findById(183L).orElse(null);
		if(board != null) {
			System.out.println(board.getPname());
			System.out.println(board.getPwriter());
			System.out.println(board.getFiles2());
		}
	}
	
	//@Test
	void deleteByBoard() {
		Long bno = 122L;
		boardRepo.deleteById(bno);
	}
	
	//@Test
	void deleteByFile() {
		Long[] arr = {117L, 120L};
		Arrays.stream(arr).forEach(bno -> {
			fileRepo.deleteById(bno);
			
		});
	}
	
	//@Test
	void selectAllBoard() {
		boardRepo.findAll().forEach(board -> {
			log.info("보드이름: " + board.getPname()
					+"--작성자: "+board.getPwriter()+"--file건수: "+board.getFiles2().size()+"건");
		});
	}
	
	//insertAll, insertAll2는 p162~164
	//@Test
	void insertAll2() { 
		IntStream.range(30, 40).forEach(i -> {
			List<PDSFile> flist = new ArrayList<>();
			IntStream.range(1, 6).forEach(j -> {
				PDSFile f = PDSFile.builder()
						   .pdsfilename("firstzone-"+j+".java")
						   .build();
				flist.add(f);
			});  
			//board에 하나 들어가고 file에 5개 들어감
			PDSBoard board = PDSBoard.builder()
					 .pname("SpringFramework수업"+i)
					 .pwriter("기범")
					 .files2(flist)
					 .build();
			boardRepo.save(board);
		});
 		
	}
	
	//@Test
	void insertAll() {
		List<PDSFile> flist = new ArrayList<>();
		IntStream.range(1, 6).forEach(i -> {
			PDSFile f = PDSFile.builder()
					   .pdsfilename("shinFile-"+i+".xls")
					   .build();
			flist.add(f);
		});  
		//board에 하나 들어가고 file에 5개 들어감
		PDSBoard board = PDSBoard.builder()
								 .pname("SpringBoot수업")
								 .pwriter("이탬")
								 .files2(flist)
								 .build();
		boardRepo.save(board);
	}
}
