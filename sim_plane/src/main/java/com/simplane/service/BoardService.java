package com.simplane.service;

import com.simplane.domain.BoardVO;

public interface BoardService {

    public void register(BoardVO board); // 게시글 등록

    public boolean remove(Long boardid);
}
