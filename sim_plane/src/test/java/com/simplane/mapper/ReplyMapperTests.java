package com.simplane.mapper;

import com.simplane.domain.ReplyVO;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class ReplyMapperTests {

    @Autowired
    private ReplyMapper mapper;

    @Test
    public void testCreate(){
        Long testid = 1L;

        IntStream.range(1, 5)
                .forEach(i->{
                    ReplyVO vo = ReplyVO.builder()
                            .testid(1L)
                            .reply("댓글 테스트" + i)
                            .replyer("replyer" + i)
                            .build();

                    mapper.create(vo);

                });
    }

    @Test
    public void testDelete(){
        int result = mapper.delete(5L);
        log.info("삭제결과 : " + result);
    }
}
