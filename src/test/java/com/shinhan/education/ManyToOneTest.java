package com.shinhan.education;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.VO.MemberDTO;
import com.shinhan.education.VO.MemberRole;
import com.shinhan.education.VO.ProfileDTO;
import com.shinhan.education.repository.MemberRepository;
import com.shinhan.education.repository.ProfileRepository;

import lombok.extern.java.Log;

@SpringBootTest
@Log
public class ManyToOneTest {
	
	@Autowired
	MemberRepository mRepo;
	@Autowired
	ProfileRepository pRepo;
	
	//멤버의 profile 갯수 얻기
	//@Test
	public void getProfileCount() {
		List<Object[]> result = pRepo.getMemberWithProfileCount();
		result.forEach(arr -> log.info(Arrays.toString(arr)));
		
		result = pRepo.getMemberWithProfileCount("user");
		result.forEach(arr -> log.info(Arrays.toString(arr)));
 	}
	
	@Test
	void addMember() {
		IntStream.range(1, 4).forEach(i -> {
			MemberDTO member = MemberDTO.builder()
										.mid("manager"+i)
										.mname("매니저"+i)
										.mpassword("")
										.mrole(MemberRole.MANAGER)
										.build();
			mRepo.save(member);
		});
	}
	
	//해당 profile의 Member정보 알아내기
	//@Test
	void getMemberByProfile() {
		Long pno = 105L;
		pRepo.findById(pno).ifPresent(p -> {
			MemberDTO member = p.getMember();
 			log.info(p.isCurrentYn() + " : " + member.getMname() + " --- " + member.getMrole());
		});
	}
	
	//특정멤버의 profile조회하기
	//@Test
	void getProfileByMember() {
		MemberDTO member = mRepo.findById("user1").orElse(null);
		pRepo.findByMember(member).forEach(profile -> {
			log.info(profile.toString());
		});
	}
	
	
	//@Test
	void profileInsertTest() {
		//'user1'의 profile5건이 있다.
		MemberDTO member = mRepo.findById("user1").orElse(null);
		/*if(member != null) {
			log.info(member.toString());
			IntStream.range(1, 6).forEach(i -> {
				ProfileDTO profile = ProfileDTO.builder()
						   .fname("face-"+i+".jpg")
						   .currentYn(i==5 ? true : false)
						   .member(member)
						   .build();
				pRepo.save(profile);
			});			
			
		}
		pRepo.findAll().forEach(profile -> {
				log.info(profile.toString());
			});*/
		
		MemberDTO member2 = mRepo.findById("user2").orElse(null);
		if(member2 != null) {
			log.info(member2.toString());
			IntStream.range(1, 6).forEach(i -> {
				ProfileDTO profile = ProfileDTO.builder()
						   .fname("hair"+".jpg")
						   .currentYn(i==5 ? true : false)
						   .member(member2)
						   .build();
				pRepo.save(profile);
			});			
			
		}
		pRepo.findAll().forEach(profile -> {
			log.info(profile.toString());
		});
	}
	
	//@Test
	void memberSelectAll() {
		mRepo.findAll().forEach(member -> {
			log.info(member.toString());
		});
	}
	
	//@Test
	void memberInsert() {
		//member table에 10명 입력하기
		IntStream.rangeClosed(1,10).forEach(i -> {
			MemberDTO member = MemberDTO.builder()
										.mid("user"+i)
										.mname("멤버"+i)
										.mpassword("1234") 
										.build();
			if(i <= 5) {
				member.setMrole(MemberRole.ADMIN);
			}else {
				member.setMrole(MemberRole.USER);
			}
			
			mRepo.save(member);
		});
	}
}
