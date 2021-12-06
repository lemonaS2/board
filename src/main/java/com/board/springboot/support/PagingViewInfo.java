package com.board.springboot.support;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingViewInfo {
	
	private int totalCount; // 총 데이터 수
	private int pageNum; // 현재 페이지
	private int pageSize; // 한 페이지에 보여주는 데이터 수
	
	private int totalPageNum; // 총 페이지 수
	private int pageGroupSize = 10; // 한 화면에 보여줄 페이지 수
	private int prevGroupPageNum; // 이전 페이지 그룹 번호
	private int nextGroupPageNum; // 다음 페이지의 그룹 번호
	private int startPageNum; // 시작 페이지 번호
	
	private int startRecodeNum; // 게시물의 No 물
	private int startRecodeNumAsc; // 게시물의 No 용
	
	private ArrayList<String> list = null;
	
	/*
	 * Constructor
	 * @param totalCount total row count
	 * @param pageNum current page number
	 * @param pageSize view item count by a page
	 * 
	 */
	public PagingViewInfo(int totalCount, int pageNum, int pageSize) {
		this.totalCount = totalCount;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		
		if(pageNum != 0) {
			this.totalPageNum = totalCount / pageSize;
			if(totalCount % pageSize > 0) {
				this.totalPageNum = this.totalPageNum + 1;
			}
		}
		
		this.prevGroupPageNum = 0;
		this.nextGroupPageNum = 0;
		
		totalPageNum = totalCount / pageSize;
		if(totalCount % pageSize > 0) {
			totalPageNum = totalPageNum + 1;
		}
		
		int startPageNum = ( ( (pageNum - 1) / pageGroupSize) ) * pageGroupSize + 1;
		
		nextGroupPageNum = startPageNum + pageGroupSize;
		if(nextGroupPageNum > totalPageNum) {
			nextGroupPageNum = 0;
		}
		
		prevGroupPageNum = startPageNum - pageGroupSize;
		if(prevGroupPageNum < 0) {
			prevGroupPageNum = 1;
		}
		
		if(pageSize > 0 && totalCount > 0) {
			list = new ArrayList<String>();
			for(int i = 0; i < pageGroupSize; i++) {
				int iPage = i + startPageNum;
				if(totalPageNum >= iPage) {
					list.add(String.valueOf(iPage));
				}
			}
		}
		this.startRecodeNum = totalCount - ((this.pageNum - 1) * this.pageSize);
		this.startRecodeNumAsc = (this.pageNum - 1) * this.pageSize;
		this.startPageNum = startPageNum;
		
	}
	
	
}
