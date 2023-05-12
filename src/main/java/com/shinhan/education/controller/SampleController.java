package com.shinhan.education.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.education.VO.CarVO;
import com.shinhan.education.repository.BoardRepository;

import lombok.extern.java.Log;

@RestController	//@Controller + @ResponseBody
				//jsp/servlet에서는... response.getWriter().append("jsp/servlet")
@Log
public class SampleController {
	
	@Autowired
	BoardRepository brepo; 
	
	@GetMapping("/friday")
	public Map<String, Object> sample1() {
		long rowCount = brepo.count();
		log.info(rowCount + "건");
		
		boolean result = brepo.existsById(10L);
		log.info(result ? "존재한다" : "존재하지않는다");
		
		Map<String, Object> map = new HashMap<>();
		map.put("aa", rowCount + "건");
		map.put("bb", result ? "존재한다" : "존재하지 않는다");
		map.put("data", brepo.findById(30L).orElse(null));

		return map;
	}
	
	@GetMapping("/cartest2")
	public List<CarVO> test4() {	//Jackson이 jav객체를 json으로 만들어ㅓ return
		
		List<CarVO> carlist = new ArrayList<>();
		IntStream.rangeClosed(1, 10).forEach(index -> {
			CarVO car1 = CarVO.builder()
					  .model("BMW520"+index)
					  .price(1000)
					  .build();
			carlist.add(car1);
		});
		
		return carlist;
	}
	
	@GetMapping("/cartest")
	public CarVO test3() {	//Jackson이 jav객체를 json으로 만들어ㅓ return
		CarVO car1 = CarVO.builder()
						  .model("BMW520")
						  .price(1000)
						  .build();
		return car1;
	}
	
	//@RequestMapping(value="/sample", method=RequestMethod.GET)은 다음으로 간편하게 작성가능
	@GetMapping("/sample1")
	public String test1() {
		return "SpringBoot 열공~";
	}
	
	@GetMapping("/sample2")
	public String test2() {
		return "SpringBoot 학습~";
	}
}
