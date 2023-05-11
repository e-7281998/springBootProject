package com.shinhan.education.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@EqualsAndHashCode
@Data
@Builder	//default생성자 제공안함. NoArgsConstructor 생성 불가 //넣고싶으면 @AllArgsConstructor도 같이 추가해야함.
@AllArgsConstructor
@NoArgsConstructor
public class CarVO {
	private String model;
	private int price;
}
