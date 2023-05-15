package com.shinhan.education;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.shinhan.education.VO.PDSBoard;
import com.shinhan.education.VO.PDSFile;
import com.shinhan.education.repository.PDSBoardRepository;
import com.shinhan.education.repository.PDSFileRepository;

@Commit
@SpringBootTest
public class OneToManyTest2 {
	@Autowired
	PDSFileRepository fileRepo;
	
	@Autowired
	PDSBoardRepository boardRepo;
	
	//@Test
	void boardFileInsert() {
		boardRepo.findById(4L).ifPresent(board -> {
			//board의 files2항목을 가져와서 그곳에 직접 값을 추가한 것임. n개
  			List<PDSFile> files2 = board.getFiles2();
  			PDSFile file1 = PDSFile.builder()
  								  .pdsfilename("추가!").build();
  			PDSFile file2 = PDSFile.builder()
					  .pdsfilename("추가!").build();
  			
  			files2.add(file1);
  			files2.add(file2);
  			//그러므로 board를 저장함.
  			//PDSBoard는 현재 cascade = CascadeType.ALL이므로 PDSBoard변경시 PDSFile도 같이 변경됨
  			boardRepo.save(board);
		});
	}

	//Board를 이용해서 File이름 수정하기
	//4번의 파일 정보를 찾아서 수정해라
	//@Test
	void boardFileUpdate() {
		boardRepo.findById(4L).ifPresent(board -> {
			//board를 찾았으니 그에 해당하는 file들(n개)도 들어왔을 것
			//board를 통해 찾고 file을 수정
			List<PDSFile> files2 = board.getFiles2();
			files2.forEach(f -> {
				f.setPdsfilename("수정!");
				fileRepo.save(f);
			});
		});
	}
	
	//PDSBoardRepository(부모)를 이용하여 자식을 수정하기
	//@Modifying를 사용한 경우 반드시 @Transactional 함께 작성해야 함
	//실행은 성공하지만 Test환경은 Rollback처리되므로 class level에 @Commit을 추가해야함
	//Test에 @Transactional : Rollback처리한다
	
//	@Transactional
//	@Test
//	void fileUpdate() {
//		boardRepo.updateFile(5L, "풍경사진");
//	}
	
	//LAZY인 경우 ... 자식에 접근하기 위해 사용
	//@Transactional
	//@Test
	void test7() {	//lazy일때 왜 오류나...?
		//fetch = FetchType.EAGER이기 때문에 다음과 같이 나옴.
		//PDSBoard(pid=4, pname=월요일이다, pwriter=이탬, 
		//files2=[PDSFile(fno=5, pdsfilename=얼굴사진1), PDSFile(fno=6, pdsfilename=얼굴사진1), PDSFile(fno=7, pdsfilename=얼굴사진1)])
		//fetch = FetchType.LAZY는 @ToString(exclude = "files2")하고 해야함
		boardRepo.findAll().forEach(b -> {
			System.out.println(b);
		});
	}
	
	//1-n : 부모에서 자식을 insert하자
	//@Test
	void test6() {
		//files2가 List<PDSFile> 타입 이므로 다음과 같이 List 타입으로 만들어줌
		List<PDSFile> files = new ArrayList<>();
		PDSFile file1 = PDSFile.builder()
							  .pdsfilename("얼굴사진1")
							  .build();
		PDSFile file2 = PDSFile.builder()
							  .pdsfilename("얼굴사진1")
							  .build();
		PDSFile file3 = PDSFile.builder()
							  .pdsfilename("얼굴사진1")
							  .build();
		
		files.add(file1);
		files.add(file2);
		files.add(file3);
		
		PDSBoard board = PDSBoard.builder()
								 .pname("월요일이다")
								 .pwriter("이탬")
								 .files2(files)
								 .build();
		boardRepo.save(board);
	}
	
 	//'자바에 칼럼이 없으므로 다음 방법은 불가.....
	//@Test
	void test5() {
		fileRepo.findAll().forEach(f -> {
			
			//fno가 1인 것 찾기
			PDSFile file = fileRepo.findById(1L).orElse(null);
			if(file != null) {
				//bon가 2인 찾아서 넣기
				//PDSBoard board = boardRepo.findById(2L).orElse(null);
				file.setPdsfilename("파일이름수정");
				fileRepo.save(file);				
			}
			
		});
	}
	
	//부모 잘 들어갔는지 확인
	//@Test
	void test4() {
		boardRepo.findAll().forEach(b -> System.out.println(b));
	}
	
	//부모만 insert
	//@Test
	void test3() {
		PDSBoard board = PDSBoard.builder()
								 .pname("게시글")
								 .pwriter("작성자")
								 .build();
		boardRepo.save(board);
	}
	
	//자식 잘 들어갔는지 확인
	//@Test
	void test2() {
		fileRepo.findAll().forEach(f -> System.out.println(f));
	}
	
	//자식만 insert
	//@Test
	public void test1() {
		//pdsno는 null인채로 들어감. 부모를 모르니
		PDSFile file = PDSFile.builder()
							  .pdsfilename("첨부파일1")
							  .build();
		fileRepo.save(file);
	}
}
