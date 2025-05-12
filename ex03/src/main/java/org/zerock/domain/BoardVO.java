package org.zerock.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 
create table tbl_board(
    bno number(10,0),
    title varchar2(200) not null,
    content varchar2(2000) not null,
    writer varchar2(50) not null,
    regdate date default sysdate, --작성일
    updatedate date default sysdate --수정일 //db에서는 카멜표기법을 안쓰고 스네이크표기법을 씀
											//또는 전부 소문자 아니면 대문자로 씀 

);
*/

@Data // => setter, getter쓰기위함 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {

	private Long bno; // Long은 래퍼클래스, null값을 허용하기 위함
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate; // 자바에서는 카멜표기법으로 써서 D를 대문자씀 
}
