package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper mapper;
	
	@Test
	public void testRead() {
		log.info(mapper.read(1L));
	}

	@Test
	public void testGetList() {
		List<BoardVO> list = mapper.getList();
	
		for(BoardVO vo : list) {
			log.info(vo);
		}
	}

	@Test
	public void testInsert() {
		BoardVO vo = BoardVO.builder()
				.title("test title")
				.content("test content")
				.writer("test00")
				.build();
		
		mapper.insert(vo);
	}

	@Test
	public void testInsertKey() {
		BoardVO vo = BoardVO.builder()
				.title("test title")
				.content("test content")
				.writer("test00")
				.build();
		
		mapper.insertSelectKey(vo);
	}
	
	@Test
	public void testDelete() {
		int result = mapper.delete(7L);
		log.info("result >>> " + result); //삭제성공 1, 삭제실패 0
	}
	
	@Test
	public void testUpdate() {
		BoardVO vo = BoardVO.builder()
				.title("수정 제목")
				.content("수정 내용")
				.writer("update00")
				.bno(6L)
				.build();
		
		int result = mapper.update(vo);
		log.info("result >>> " + result); //수정성공 1, 수정실패 0
	}
	
	@Test
	public void testPaggin() {
		List<BoardVO> list = mapper.getListWithPaging(new Criterial(3,10));
		list.forEach(board->log.info(board)); //board에 있는걸 반복해서 찍어보겠다.
	}
	
	
	@Test //map이 json과 궁합이 잘맞아서 잘쓰임(키,벨류형태)
	public void testSearch() {
		Map<String , String> map = new HashMap<String, String>();
		
		map.put("T", "어버이날"); //T : title
		map.put("C", "은혜"); // C : Content
		map.put("W", "졸려"); // W : writer
		
		Map<String, Map<String, String>> outer = new HashMap<>();
		
		outer.put("map", map);
//		log.info( (outer.get("map")) );
//		log.info( (outer.get("map")).get("T") );
	
		List<BoardVO> list = mapper.searchTest(outer);
		
		log.info("-------------");
		log.info(list);
	}
	
	@Test 
	public void testSearch2() {
		Criterial cri = new Criterial();
		
		cri.setKeyword("수정");
		cri.setType("TW");
		
		mapper.getListWithPaging(cri) //BoardMapper.xml로 이동해서 해당부분에서 실행시켜줌 
		.forEach(board-> log.info(board));
	}
	
	@Test 
	public void testTotalCount() {
		Criterial cri = new Criterial();
		
		cri.setKeyword("수정");
		cri.setType("TW");
		
		log.info("total count : ");
		log.info(mapper.getTotalCount(cri));
		
	}
	

	
	
}
