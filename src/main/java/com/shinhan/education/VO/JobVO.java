package com.shinhan.education.VO;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_jobs")
public class JobVO {
	@Id
	private String jobId;
	@Column(unique = true, nullable = false, length = 35)
	private String jobTitle;
	private int maxSalary;
	private int minSalary;
	@CreationTimestamp	//입력시
	private Timestamp regDate;	
	@UpdateTimestamp	//입력시 + 수정시
	private Timestamp updateDate;	
}
