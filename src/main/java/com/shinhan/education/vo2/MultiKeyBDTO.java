package com.shinhan.education.vo2;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="tbl_child2")
public class MultiKeyBDTO {
	@EmbeddedId
	MultiKeyB id;
	
	private String userName;
	private String address;
}
