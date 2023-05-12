package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shinhan.education.VO.BoardVO;

//interface 설계... CRUD 작업을 위해
//구현은 JPA가 한다.
//<상속받을 테이블, key값의 tyle>
//상속받을 테이블에는 클래스이름 들어감
//기본은 : findAll(), findById(), save()
@Repository
public interface BoardRepository extends CrudRepository<BoardVO, Long>{
	//조건조회를 추가하기.. 다음을 참고
	//https://docs.spring.io/spring-data/jpa/docs/2.5.1/reference/html/#jpa.query-methods
	public List<BoardVO> findByTitle(String title);
	public List<BoardVO> findByContent(String content);
	public List<BoardVO> findByWriter(String writer);
	public List<BoardVO> findByWriterAndTitle(String writer, String title);

	public List<BoardVO> findByTitleContaining(String title);	//where title like '%?%'
	public List<BoardVO> findByTitleStartingWith(String title);	//where title like '?%'
	public List<BoardVO> findByTitleEndingWith(String title);	//where title like '%?'
	public List<BoardVO> findByTitleNotLike(String title);
	//title이 like'%?%' and bno > ?
	public List<BoardVO> findByTitleContainingAndBnoGreaterThan(String title, Long bno);
	//where bno between ? and ? order by bno desc
	public List<BoardVO> findByBnoBetweenOrderByBnoDesc(Long bno1, Long bno2);
	//where content is null
	public List<BoardVO> findByContentIsNull();	//함수 작성시 ~Isnull() 또는 ~Null()
 
	public List<BoardVO> findByContentIgnoreCase(String content);	//where upper(Content) = upper(?)
 
	//where Title like ? order by Title desc
	public List<BoardVO> findByTitleContainingOrderByTitleDesc(String title);
	
	//title로 조회, sort desc, paging => page, size
 	public List<BoardVO> findByTitleContainingOrderByTitleDesc(String title, Pageable paging);
	public List<BoardVO> findByTitleContaining(String title, Pageable paging);
	public Page<BoardVO> findByBnoGreaterThan(Long bno, Pageable paging);
}
