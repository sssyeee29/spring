package org.zerock.mapper;

import java.util.List;
import java.util.Map;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

public interface BoardMapper {

	public List<BoardVO> getList();

	public BoardVO read(Long bno);

	public void insert(BoardVO board); //데이터만 집어넣기 

	public void insertSelectKey(BoardVO board); //데이터를 추가하면서 몇번째인지도 같이 알수있음
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public List<BoardVO> getListWithPaging(Criterial cri); //페이징 처리가 되어있는 전체목록

	public int getTotalCount(Criterial cri); // 전체데이터 페이지 조회 
	
	public List<BoardVO> searchTest(Map<String , Map<String,String>> map);
}
