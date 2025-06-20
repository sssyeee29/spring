package org.zerock.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

public interface BoardMapper {

	public List<BoardVO> getList(); // 게시판 모든 게시글 목록 조회 

	public BoardVO read(Long bno); // 특정 게시글 하나 bno(게시글 번호)로 조회

	public void insert(BoardVO board); //게시글을 새로 DB에 삽입 

	public void insertSelectKey(BoardVO board); //게시글 삽입하면서 자동 생성된 게시글 번호도 동시에 가져옴 
	
	public int delete(Long bno); // 게시글 번호로 삭제 
	
	public int update(BoardVO board); //게시글 내용 수정 
	
	public List<BoardVO> getListWithPaging(Criterial cri); //페이징 처리가 되어있는 게시글 전체 목록 조회

	public int getTotalCount(Criterial cri); // 조건에 맞는 전체 게시글 수 조회 
	
	public List<BoardVO> searchTest(Map<String , Map<String,String>> map);

	public void updateReplyCnt(@Param("bno")Long bno, @Param("amount") int amount); //amount는 추가하거나 삭제할 갯수
}
