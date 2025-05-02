package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardMapper {

	public List<BoardVO> getList();

	public BoardVO read(Long bno);

	public void insert(BoardVO board); //데이터만 집어넣기 

	public void insertSelectKey(BoardVO board); //데이터를 추가하면서 몇번째인지도 같이 알수있음
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	
}
