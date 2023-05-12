package com.shinhan.education.VO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Entity
@Table(name = "tbl_pdsboard")
@EqualsAndHashCode(of = "pid")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PDSBoard {	//p162~164
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long pid;
private String pname;
private String pwriter;
    //cascade:영속성전이 PDSBoard변경시 PDSFile변경)
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER) //즉시로딩 : fetch = FetchType.EAGER : 나를 조회하면 PDSFile(자식)쪽도 같이 호출됨
    //JPA는 default로 지연로딩을 사용한다.(PDSBoard조회시 PDSFile 조인하지않음) 
    //1)fetch = FetchType.LAZY ....PDSFile과 연결불가, @Query로 해결 
    //2)fetch = FetchType.EAGER....PDSFile과 연결가능 
    @JoinColumn(name="pdsno") //PDSFile칼럼에 pdsno생성 	//중간테이블 생기는 것 막아줌
    private List<PDSFile> files2;	//files2 변수는 칼럼으로 생성되지 않음
}
