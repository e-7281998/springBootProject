package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shinhan.education.VO.BoardVO;

@Repository
public interface StudyBoardRepository extends CrudRepository<BoardVO, Long>{
	
	@Query("select b from BoardVO b where b.title like %?1%"
			+ " and b.content like %?2% order by b.bno desc")
	public  List<BoardVO> findByTitle(String title, String content);
	
	@Query("select b from BoardVO b where b.title like %:tt%"
			+ " and b.content like %:cc% order by b.bno desc")
	public List<BoardVO> findByTitle2(@Param("tt") String title,@Param("cc") String content);

	@Query("select b from BoardVO b where b.bno > 300 order by b.bno desc")
	public List<BoardVO> findByPage(Pageable pageable);
}
