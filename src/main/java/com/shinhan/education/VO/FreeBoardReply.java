package com.shinhan.education.VO;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
@EqualsAndHashCode(of= {"rno"})
@Entity
@Builder
@Table(name="tbl_free_replies")
public class FreeBoardReply {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rno;
	private String reply;
	private String replyer;
	@CreationTimestamp
	private Timestamp regDate;
	@UpdateTimestamp
	private Timestamp updateDate;
	
	//연관관계 설정 : n-1
	//FK : tbl_free_replies에 칼럼이 board_bno로 생성됨
	@ManyToOne
	FreeBoard board;
}
