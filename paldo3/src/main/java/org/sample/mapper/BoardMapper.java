package org.sample.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;

public interface BoardMapper {

	public void insertBoard(BoardVO board);
	
	public void insertSelectKeyBoard(BoardVO board);

	public BoardVO readBoard(Long boardid);

	public int updateBoard(BoardVO board);

	public int deleteBoard(@Param("boardid") Long boardid);
    
//	public List<BoardVO> selectAll(Criteria cri);
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public int countTotal(Criteria cri);
	
	public List<BoardVO> BoardWithUserProductMap();

	@ResultMap("BoardWithUserProductMap")
	public List<BoardVO> getList(Criteria cri);
	
//	public List<BoardVO> searchTest(Map<String, Map<String,String>> map);
	
}
