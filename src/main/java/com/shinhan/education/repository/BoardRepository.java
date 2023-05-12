package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shinhan.education.VO.BoardVO;

//interface 설계... CRUD 작업을 위해
//구현은 JPA가 한다.
//<상속받을 테이블, key값의 tyle>
//상속받을 테이블에는 클래스이름 들어감
//1. 기본은 : findAll(), findById(), save(), count(), exists()
//2. 규칙에 맞는 메서드 추가 : findByXXXX
//3. JQPL 사용 : @Query
//4. JQPL 사용 : @Query, nativeQuery = true 를 사용하면 직접 SQL 작성이 가능하다.
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
	

	//JPQL ( JPL Query Language ) ... * 지원안됨
	@Query("select b from BoardVO b where b.title like %?1% "
			+ "and b.content like %?2% order by b.bno desc")
	public List<BoardVO> findByTitle2(String title, String content);
	
	//파라미터가 들어감 
	@Query("select b from BoardVO b where b.title like %:tt% "
			+ "and b.content like %:cc% order by b.bno desc")
	public List<BoardVO> findByTitle3(@Param("tt") String title,@Param("cc") String content);
	
	@Query("select b from #{#entityName} b where b.title like %?1% "
			+ "and b.content like %?2% order by b.bno desc")
	public List<BoardVO> findByTitle4(String title, String content);
	
	@Query("select b.title, b.content, b.writer from #{#entityName} b where b.title like %?1% "
			+ "and b.content like %?2% order by b.bno desc")
	//b.title, b.content, b.writer...type이 섞여있어서 BoardVO로 받을 수 없다. Object배열로 받아야 함
	public List<Object[]> findByTitle5(String title, String content);
 
	//원래 sql문을 직접 작성하는 경우 nativeQuery=true(남용하지 않기) *지원...  
	@Query(value = "select * from t_boards where title like '%'||?1||'%' "
			+ "and content like '%'||?2||'%' order by bno desc", nativeQuery = true)
	//b.title, b.content, b.writer...type이 섞여있어서 BoardVO로 받을 수 없다. Object배열로 받아야 함
	public List<BoardVO> findByTitle6(String title, String content);
	
}
