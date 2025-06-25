package com.simplane.mapper;

import com.simplane.domain.BoardVO;
import com.simplane.domain.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardMapper {

    public BoardVO read(Long boardid);

    public List<BoardVO> readAll();

    public List<BoardVO> getListWithPaging(Criteria cri);

    public void create(BoardVO board); // 게시글 등록

    public int update(BoardVO board);

    public void createSelectKey(BoardVO board); // 게시글 등록하면 게시글의 번호도 자동으로 같이 가져옴

    public int delete(Long boardid); //게시글 삭제

    public int getTotalCount(Criteria cri);

    public void updateReplyCnt(@Param("boardid")Long boardid, @Param("replycnt")Integer replycnt);

}
