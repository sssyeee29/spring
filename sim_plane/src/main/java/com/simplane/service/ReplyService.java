package com.simplane.service;


import com.simplane.domain.ReplyVO;

public interface ReplyService {

    public int create(ReplyVO vo);

    public int delete(Long replyid);

}
