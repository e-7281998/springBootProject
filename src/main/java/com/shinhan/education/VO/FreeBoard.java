package com.shinhan.education.VO;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
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
@ToString 
@EqualsAndHashCode(of= {"bno", "title"})
@Entity
@Builder
@Table(name="tbl_freeboards")
public class FreeBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bno;
	private String title;
	private String writer;
	private String content;
	@CreationTimestamp
	private Timestamp regDate;
	@UpdateTimestamp
	private Timestamp updateDate;
	
	//연관관계 설정 : 1-n
	@OneToMany(cascade = CascadeType.ALL,
				mappedBy = "board",	//board 칼럼과 관련있다. board에 매여있음
				fetch = FetchType.EAGER)
	List<FreeBoardReply> replies;
}
