package com.shinhan.education;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.MultiKeyBRepository;
import com.shinhan.education.repository.MultiKeyRepository;
import com.shinhan.education.vo2.MultiKeyAUsing;
import com.shinhan.education.vo2.MultiKeyB;
import com.shinhan.education.vo2.MultiKeyBDTO;

@SpringBootTest
public class MultiKeyTest {

	@Autowired
	MultiKeyRepository multiKeyRepo;
	
	@Autowired
	MultiKeyBRepository bRepo;
	
	@Test
	void Test2() {
		MultiKeyB id = MultiKeyB.builder()
								.id1(10)
								.id2(21)
								.build();
		MultiKeyBDTO b = MultiKeyBDTO.builder()
									 .id(id)
									 .userName("ltm")
									 .address("서울2")
									 .build();
		bRepo.save(b);
	}
	
	
	//@Test
	void test1() {
		MultiKeyAUsing a = new MultiKeyAUsing();
		a.setId1(1);
		a.setId2(2);
		a.setUserName("tm");
		a.setAddress("jeju");
		multiKeyRepo.save(a);
 	}
}
