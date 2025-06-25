package com.simplane.domain;

import lombok.Builder;

import java.util.Date;

@Builder
//댓글
public class ReplyVO {

    private Long replyid; //댓글번호
    private Long testid; //테스트번호
    private String reply; //댓글내용
    private String replyer; //작성자
    private Date replydate; //댓글등록일
}
