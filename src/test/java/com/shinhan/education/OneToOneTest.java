package com.shinhan.education;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.UserCellPhoneVORepository;
import com.shinhan.education.repository.UserCellPhoneVORepository2;
import com.shinhan.education.repository.UserVO3Repository;
import com.shinhan.education.repository.UserVORepository;
import com.shinhan.education.vo2.UserCellPhoneVO;
import com.shinhan.education.vo2.UserCellPhoneVO3;
import com.shinhan.education.vo2.UserCellphoneVO2;
import com.shinhan.education.vo2.UserVO;
import com.shinhan.education.vo2.UserVO2;
import com.shinhan.education.vo2.UserVO3;

@SpringBootTest
public class OneToOneTest {
	
	@Autowired
	UserVORepository uRepo;
	
	@Autowired
	UserCellPhoneVORepository pRepo;
	
	@Autowired
	UserCellPhoneVORepository2 uRepo2;
	
	@Autowired
	UserVO3Repository uRepo3;
	
	
	@Test
	void test3() {
		UserCellPhoneVO3 phone = UserCellPhoneVO3.builder()
												 .phoneNumber("011-1231-2312")
												 .model("갤럭시")
												 .build();
		UserVO3 user = UserVO3.builder()
							  .userid("zz")
							  .username("Ju")
							  .phone(phone)
							  .build();
		phone.setUser(user);
		uRepo3.save(user);
	}
	
	//@Test
	void test2() {
		UserVO2 user = UserVO2.builder()
				.userid("good2")
				.username("홍대")
 				.build();
		UserCellphoneVO2 phone = UserCellphoneVO2.builder()
				   .phoneNumber("010-1234-5678")
				   .model("아이폰12mini")
				   .user(user)
				   .build();
		uRepo2.save(phone);
 	}
	
	//@Test
	void test1() {
		UserCellPhoneVO phone = UserCellPhoneVO.builder()
											   .phoneNumber("010-1234-5678")
											   .model("아이폰12mini")
											   .build();
		UserCellPhoneVO savePhone = pRepo.save(phone);
		UserVO user = UserVO.builder()
							.userid("good")
							.username("홍대")
							.phone(savePhone)
							.build();
		uRepo.save(user);
	}
}
