package com.shinhan.education.vo2;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_user2")
public class UserVO2  {
	@Id
	@Column(name="user_id")
	private String userid;
	private String username;
	
	//대상테이블에서 참조하기
	
}
