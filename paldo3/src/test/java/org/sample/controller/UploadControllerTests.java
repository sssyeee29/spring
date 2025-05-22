package org.sample.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
    "file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@WebAppConfiguration
@Log4j
public class UploadControllerTests {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testUploadImage() throws Exception {
        // 1. 실제 파일 경로 지정
        String filePath = "C:/upload/iphone_se2_silver.jpg";

        // 2. 파일 스트림으로 MockMultipartFile 생성
        FileInputStream fis = new FileInputStream(filePath);
        MockMultipartFile mockFile = new MockMultipartFile(
            "uploadFile",                         // 컨트롤러의 파라미터 이름
            "iphone_se2_silver.jpg",              // 업로드될 파일명
            "image/jpeg",                         // 컨텐츠 타입
            fis                                   // 파일 스트림
        );
    
        // 3. 테스트 요청 수행
        mockMvc.perform(multipart("/post/upload")
                .file(mockFile)
                .param("title", "테스트 제목")
                .param("content", "테스트 내용")
                .param("writer", "테스트 작성자")
            )
            .andExpect(status().is3xxRedirection()); // 리다이렉트 응답 확인

        log.info("Upload 테스트 완료: " + mockFile.getOriginalFilename());
    }
}
