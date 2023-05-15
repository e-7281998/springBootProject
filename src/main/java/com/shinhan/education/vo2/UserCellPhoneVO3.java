package com.shinhan.education.vo2;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="tbl_usercellphone3")
public class UserCellPhoneVO3 {	//양방향
	//식별자로 참조하기(참조하는 키를 PK로 사용)
	//JPA에서 반드시 ID가 존재해야 한다.
	@Id
	String userid;
	
	//ID가 식별자이다.(PK, FK)
	@MapsId	//UserVO#의 userid를 mapping
	@OneToOne
	@JoinColumn(name="user_id")
	UserVO3 user;

	private String phoneNumber;
	private String model;
	
}
