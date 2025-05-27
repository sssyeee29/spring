package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criterial;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class ReplyServiceImpl implements ReplyService{

	//tbl_board, tbl_reply 둘 다 같이 댓글갯수 추가를 시켜야하니까 여기에 두개를 써준거
	private final ReplyMapper mapper;
	private final BoardMapper boardMapper;
	
	@Transactional //
	@Override //댓글추가 
	public int register(ReplyVO vo) {
		boardMapper.updateReplyCnt(vo.getBno(), 1); //댓글 1개 추가 //댓글 한개 추가시 댓글갯수가 하나 증가했는데 
		return mapper.insert(vo); //여기서 오류가 나면 안됨 
	}

	@Override
	public ReplyVO get(Long rno) {
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		return mapper.update(vo);
	}

	@Transactional //밑에 boardmapper.~~ 이거랑 return문에서 둘이 동시에 처리가 안됐을시 원상복귀하라는 
	@Override
	public int remove(Long rno) {
		
		ReplyVO vo = mapper.read(rno); //vo.getBno() 이 값을 알 수 없기때문에 vo를 한 번 더 조회를 해줘야함 
		
		boardMapper.updateReplyCnt(vo.getBno(), -1); //댓글 1개 감소 
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criterial cri, Long bno) {
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criterial cri, Long bno) {
		return new ReplyPageDTO(mapper.getCountByBno(bno), //mapper에 있는 목록에 해당하는 댓글 갯수 
								mapper.getListWithPaging(cri, bno));
	}

	
	
}





















