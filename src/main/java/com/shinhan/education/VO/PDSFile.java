package com.shinhan.education.VO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity	//JPA가 관리
@Table(name="tbl_pdsfiles")
@EqualsAndHashCode(of="fno")	//(of="fno") :fno가 같으면 같음 //작성 안하면 모든 칼럼이 같으면 같음
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PDSFile {	//p162~164
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fno;
	private String pdsfilename;
}
