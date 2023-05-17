package com.shinhan.education.vo3;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class PageVO {	//페이지 하나를 만듬
	private static final int DEFAULT_SIZE = 10;	//한 페이지에 기본으로 10개
	private static final int DEFAULT_MAX_SIZE = 50;		//한 페이지에 50개 이상 불가
	private int page;
	private int size;	
	//검색조건처리위해 field추가
	private String keyword;
	private String type;		
	public PageVO() {
		this.page = 1;
		this.size = DEFAULT_SIZE;
	}
	public void setSize(int size) {
		this.size = size<DEFAULT_SIZE || size > DEFAULT_MAX_SIZE?
				DEFAULT_SIZE:size;
	}
	public Pageable makePageable(int direction, String ...prop) {
		Sort.Direction dir = direction == 0 ? Sort.Direction.DESC : Sort.Direction.ASC;
		return PageRequest.of(this.page-1, this.size, dir, prop);
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setType(String type) {
		this.type = type;
	}
}
