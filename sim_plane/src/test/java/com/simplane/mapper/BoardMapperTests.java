package com.simplane.mapper;

import com.simplane.domain.BoardVO;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testInsert() {
        BoardVO vo = BoardVO.builder()
                .title("test title")
                .content("test content")
                .imagePath("/images/test.jpg")
                .build();

        boardMapper.create(vo);
    }

    @Test
    public void testDelete() {
        int result = boardMapper.delete(2L);
        log.info("result >>>" + result);
    }
}
