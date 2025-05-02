package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.dto.BoardVO;
import org.zerock.persistence.DataSourceTests;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {

	@Autowired
	private TimeMapper timeMapper;
	
	@Test
	public void testGetTime() { //mybatis를 통해 DB에 sql구문을 전송하는 과정을 테스트?하는거 
		log.info("------------------------------------");
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime());
	}
	
	@Test
	public void testGetTime2() {
		log.info("-------------------------");
		log.info(timeMapper.getTime2());
	}

	@Test //전체데이터 가져오는 테스트
	public void testAllList() {
		List<BoardVO> list = timeMapper.selectAllList();
		for(BoardVO vo : list)
			log.info(vo);
	}
	
	@Test //단 건 데이터 가져오기
	public void testSelectOne() {
		log.info(timeMapper.selectOneByNum(5));
	}
	
	@Test
	public void testInsert() {
		BoardVO vo = new BoardVO();
		
		vo.setName("마루");
		vo.setEmail("aaa@aaa.com");
		vo.setPass("1234");
		vo.setTitle("마루는 강쥐");
		vo.setContent("마루킁킁");
		
		timeMapper.insertBoard(vo);
		
	}
}
