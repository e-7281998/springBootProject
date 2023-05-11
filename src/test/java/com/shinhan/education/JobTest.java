package com.shinhan.education;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.VO.JobVO;
import com.shinhan.education.repository.JobRepository;

@SpringBootTest
public class JobTest {
	
	@Autowired
	JobRepository repo;
	
	//@Test
	public void test6() {	//전체 삭제
		repo.deleteAll();
	}
	
	//@Test
	public void test5() {	//특정항목삭제
		repo.findById("직책코드2").ifPresent(job -> {
			repo.delete(job);
		});
	}
	
	//@Test
 	public void test4() {	//정보 수정
		//CRUD...Update
		 repo.findById("직책코드3").ifPresent(job -> {
			 job.setJobTitle("SM개발자-수정");
			 job.setMaxSalary(1500);
			 job.setMinSalary(3000);
			 repo.save(job);			 
		 });
		 test2();
	} 
	
	//@Test
	public void test3() {	//특정직원 조회
		//CRUD...Read
		Optional<JobVO> jobOptional = repo.findById("직책코드11");
		if(jobOptional.isPresent()) { //값이 있다면
			JobVO job = jobOptional.get();
			System.out.println(job);
		}else {
			System.out.println("존재하는 직책이 없습니다.");
		}
		
		JobVO job = repo.findById("직책코드1"). orElse(null);
		if(job == null) {
			System.out.println("존재하는 직책이 없습니다.");
		}else {
			System.out.println(job);
		}
	}
	
	//@Test
	public void test2() {	//전체 조회
		//CRUD...Read
		Iterable<JobVO> datas = repo.findAll();
		List<JobVO> joblist = (List<JobVO>)datas;
		for(JobVO job: joblist) {
			System.out.println(job);
		} 
	}
	
	//@Test
	public void test1() {
		//CRUD...create
		String[] arr = {"마케팅", "SI개발자","SM개발자", "매니저","기획자",
						"마케팅2", "SI개발자2","SM개발자2", "매니저2","기획자2"};
		IntStream.range(0, arr.length).forEach(i -> {
			JobVO job = JobVO.builder()
							 .jobId("직책코드" + i)
							 .jobTitle(arr[i])
							 .minSalary(1000)
							 .maxSalary(4000)
							 .build();
			repo.save(job);
		});
	}
}
