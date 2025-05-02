package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.dto.BoardVO;

public interface TimeMapper {

	@Select("select sysdate from dual")
	public String getTime(); //어노테이션으로 읽어오겠다.
	
	public String getTime2(); //xml방식으로 읽어오겠다.
	
	public List<BoardVO> selectAllList(); //DB에서 전체리스트 가져오기
	
	public BoardVO selectOneByNum(int num); //단건 데이터 조회
	
	public void insertBoard(BoardVO vo); 
}


