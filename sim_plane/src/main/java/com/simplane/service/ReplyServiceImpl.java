package com.simplane.service;

import com.simplane.domain.ReplyVO;
import com.simplane.mapper.BoardMapper;
import com.simplane.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapper mapper;
    private final BoardMapper boardMapper;


    @Override
    public int register(ReplyVO vo) {
        boardMapper.updateReplyCnt(vo.getreply());
    }

    @Override
    public int delete(Long replyid) {
        return 0;
    }
}