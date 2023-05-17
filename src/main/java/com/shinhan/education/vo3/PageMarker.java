package com.shinhan.education.vo3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

@Getter
@ToString(exclude = "pageList")
@Log
public class PageMarker<T> {	//페이지전체를 만들어줌
	
	//사용하고 싶은 페이지 타입이 T에 들어감...  제네릭
	private Page<T> result;	//페이지 데이터
	private Pageable prevPage; //이전으로 이동하는데 필요한 정보를 가짐
	private Pageable nextPage;
	private Pageable currentPage;
	private int currentPageNum;  //화면에 보이는 1부터 시작하는 페이지번호
	private int totalPageNum;
	private List<Pageable> pageList;
	
	public PageMarker(Page<T> result, int pageSize) {
		this.result = result;
		this.currentPage = result.getPageable();
		this.currentPageNum = currentPage.getPageNumber()+1;
		this.totalPageNum = result.getTotalPages();
		this.pageList = new ArrayList<Pageable>();
		calcPage(10);
	}
	
	public void calcPage(int cnt) {
		//현재 페이지가 3이라면... 3/(10*1.0)
		int tempEndNum = (int)(Math.ceil(currentPageNum/(cnt*1.0)*cnt));	//현재 페이지의 가장 마지막
		int startNum = tempEndNum - (cnt - 1);
		Pageable startPage = this.currentPage;
		
		//현재 페이지의 시작하는 row가 현재 page수보다 작다면 시작페이지는 전페이지
		for(int i = startNum; i<this.currentPageNum; i++) {
			startPage = startPage.previousOrFirst();
		}
		
		this.prevPage = startPage.getPageNumber()<=0?null:startPage.previousOrFirst();
		
		log.info("tempEndNum:" + tempEndNum);
		log.info("totalPageNum:" + totalPageNum);
		
		//다음페이지가 있는지 없는지 계산... 
		if(this.totalPageNum<tempEndNum) {
			tempEndNum = this.totalPageNum;
			this.nextPage = null;
		}
		
		for(int i = 1; i<=totalPageNum; i++) {
			pageList.add(startPage);
			startPage = startPage.next();
		}
		
		this.nextPage = startPage.getPageNumber()+1 < totalPageNum?startPage:null;
	}
}
