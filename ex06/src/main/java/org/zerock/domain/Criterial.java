package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criterial {
	
	private int pageNum; // 페이지
	private int amount; // 페이지 개수
	
	private String type; //검색조건, 제목, 내용, 작성자
	private String keyword; 
	
	public Criterial() {
		this(1,10); //기본값 : 1페이지에 10개
	}
	
	public Criterial(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null? new String[] {}: type.split(""); //String type에서 검색조건 ''' 이 애들이 붙어서 오니까 
																//여기서 split해주는거 
	}
}
