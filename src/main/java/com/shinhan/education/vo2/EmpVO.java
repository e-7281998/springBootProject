package com.shinhan.education.vo2;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter 
@ToString
@Entity
@Table(name = "tbl_emp_1")
public class EmpVO {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer employee_id;
	 private String first_name;
	 private String last_name;
	 private String email;
	 private String phone_number;
	 private Date hire_date;
	 private String job_id;
	 private Double salary;	
  	 private Double commission_pct;
	 private Integer manager_id;
	 
	 //1번
	 @ManyToOne
	 private DeptVO dept;
	 //칼럼이름은 dept_department_id로 생성됨
}
