package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.education.VO.FreeBoard;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long>{
	//where bno > ?
	List<FreeBoard> findByBnoGreaterThan(Long bno, Pageable paging);
}
