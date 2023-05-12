package com.shinhan.education.repository;

import org.springframework.data.repository.CrudRepository;

import com.shinhan.education.VO.MemberDTO;

public interface MemberRepository extends CrudRepository<MemberDTO, String>{
	
}
