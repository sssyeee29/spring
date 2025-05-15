package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service //서비스계층이라서 추가 
@Log4j
@RequiredArgsConstructor // 생성자주입
public class BoardServiceImpl implements BoardService{

	//@RequiredArgsConstructor + final => 객체주입(생성자)
	private final BoardMapper mapper; //BoardMapper로 주입을 받아서 DB내용을 가져옴
	
	@Override
	public void register(BoardVO board) {
		log.info("register......" + board);
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get......" + bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify......");
		return mapper.update(board) == 1; //성공해서 1이 나오면 true가 나옴 
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove......");		//삭제된 행의 갯수가 1이니까 성공하면 1, 삭제 못하면 0개를 삭제한거니까 0이 나옴
		return mapper.delete(bno) == 1; //성공해서 1이 나오면 true가 나옴 
	}

	@Override //전체 데이터 가져오기
	public List<BoardVO> getList(Criterial cri) {
		log.info("getList.....");
		return mapper.getListWithPaging(cri); 
	}

	@Override
	public int getTotal(Criterial cri) {
		log.info("getTotal.................");
		return mapper.getTotalCount(cri);
	}

}
