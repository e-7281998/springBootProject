package com.shinhan.education.VO;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity	//JAP가 관리
@Table(name = "t_boards2")	//name설정하지 않으면 클래스명으로 테이블명이 생성됨
public class BoardVO2 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	//자동 생성 //GenerationType.AUTO는 DB에 따라 규칙 알아서해줌
	private Long bno;
	@NonNull	//생성시 반드시 값이 있어야 함(lombok)	 //java에서
	@Column(nullable = false)	//DB에 칼럼이 NOT NULL		//DB에서
	private String title;
	@Column(length = 100)	//길이 정해줌
	private String content;
	private String writer;
	@CreationTimestamp	//insert시 시각이 자동으로 입력
	private Timestamp regdate;
	@UpdateTimestamp	//update시 수정시각 입력
	private Timestamp updatedate;
}
