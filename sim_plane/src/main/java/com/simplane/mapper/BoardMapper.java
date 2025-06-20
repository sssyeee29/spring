package com.simplane.mapper;

import com.simplane.domain.BoardVO;

public interface BoardMapper {


    public void create(BoardVO board); // 게시글 등록

    public void createSelectKey(BoardVO board); // 게시글 등록하면 게시글의 번호도 자동으로 같이 가져옴

    public int delete(Long boardid); //게시글 삭제
}
