package org.sample.mapper;

import java.util.List;

import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;

public interface BoardMapper {

	public List<BoardVO> getList();
	
	public BoardVO read(Long pno);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public int getTotalCount(Criteria cri);
	
	public List<BoardVO> getListByBrand(String brand);
	
	
}
