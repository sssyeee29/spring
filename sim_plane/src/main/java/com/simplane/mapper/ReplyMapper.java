package com.simplane.mapper;

import com.simplane.domain.BoardVO;
import com.simplane.domain.ReplyVO;

import java.util.List;

public interface ReplyMapper {

    public void create(ReplyVO vo); //댓글 등록

    public int delete(Long replyid); // 댓글 삭제

}
