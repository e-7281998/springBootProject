package com.shinhan.education.vo2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="tbl_usercellphone")
public class UserCellPhoneVO {
	
	@Id
	@Column(name="phone_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long phoneId;
	private String phoneNumber;
	private String model;
	
}
