package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter // 조회만 하겠다는 이야기
@ToString
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	//전체 데이터 갯수
	private int total;
	
	//페이지정보, 페이지당 데이터 갯수(pageNum, amount)
	private Criterial cri;
	
	public PageDTO(Criterial cri, int total) {
		this.cri = cri; 
		this.total = total;
		
		//endPage - 끝페이지
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10; 
		
		//startPage - 시작페이지
		this.startPage = this.endPage - 9;
		
		//전체 페이지 목록에서 마지막 페이지 
		int realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		
	}
	
	
}
