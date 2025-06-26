package com.simplane.service;


import com.simplane.domain.ReplyVO;

public interface ReplyService {

    public int register(ReplyVO vo);

    public int remove(Long replyid);

}
