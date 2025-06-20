//package com.simplane.service;
//
//import com.simplane.domain.ReplyVO;
//import com.simplane.mapper.ReplyMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class ReplyServiceImpl implements ReplyService {
//
//    private final ReplyMapper mapper;
//
//    @Transactional
//    @Override
//    public int register(ReplyVO vo){
//        log.info("registerReply..." + vo);
//        mapper.create(vo);
//        return 1; //성공시 1 반환
//    }
//
//    @Override
//    public int remove(Long replyid){
//        log.info("removeReply..." + replyid);
//        return mapper.delete(replyid) == 1;
//    }
//}
