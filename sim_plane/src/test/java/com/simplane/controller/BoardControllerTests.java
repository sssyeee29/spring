package com.simplane.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
                        "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j
@WebAppConfiguration // 서버없이 테스트 할 때 필요함 
public class BoardControllerTests {

    @Autowired // 웹 관련 빈(생성된 객체) 관리
    private WebApplicationContext ctx;

    private MockMvc mockMvc; // 서버 실행없이 HTTP 요청과 응답을 시뮬레이션하기 위한 도구

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testRegister() throws Exception {
        String resultPage = mockMvc.perform(MockMvcRequestBuilders
                .post("/board/register")
                .param("title", "테스트 새글 제목")
                .param("content", "테스트 새글 내용")
                .param("imagePath", "/images/test.jpg")
                ).andReturn()
                .getModelAndView()
                .getViewName();

        log.info("resultPage: {}", resultPage);
    }
}






















