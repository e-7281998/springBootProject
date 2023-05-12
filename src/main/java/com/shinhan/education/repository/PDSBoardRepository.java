package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.education.VO.PDSBoard;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long>{
	//fetch = FetchType.LAZY인 경우 JPQL를 이용할 수 있다.
	@Query("select b.pname, b.pwriter, f.pdsfilename "
			+ " from PDSBoard b left outer join b.files2 f "
			+ " where b.pid = ?1 order by b.pid ")
	public List<Object[]> getFilesInfo(long pid); 
	
	//fetch = FetchType.LAZY인 경우 nativeQuery를 이용할 수 있다.
	@Query(value = "select board.pname, count(*)"
			+ " from tbl_pdsboard board left outer join tbl_pdsfiles file2"
			+" on(board.pid = file2.pdsno)"
			+ " group by board.pname ", nativeQuery = true)
	public List<Object[]> getFilesInfo2();
}
