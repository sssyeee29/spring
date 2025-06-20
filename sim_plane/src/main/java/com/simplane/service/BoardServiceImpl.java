package com.simplane.service;

import com.simplane.domain.BoardVO;
import com.simplane.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor // + final => 객체주입(생성자)
public class BoardServiceImpl implements BoardService {

    private final BoardMapper mapper; //BoardMapper로 주입 받아서 DB내용 가져오기

    @Override
    public void register(BoardVO board){
        log.info("register..." + board);
        mapper.createSelectKey(board); // 등록하면서 게시글 번호를 vo에 자동으로 세팅하는 메소드 호출
    }

    @Override
    public boolean remove(Long boardid){
        log.info("remove...");
        return mapper.delete(boardid) == 1; //삭제 성공시 1반환 => true, false로 변환
    }


}
