package com.simplane.controller;

import com.simplane.domain.ReplyVO;
import com.simplane.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping(value = "/new")
    public ResponseEntity<String> create(@RequestBody ReplyVO vo){ //@RequestBody -> json형식으로 받겠다
        log.info("ReplyVO : " + vo);

        int insertCount = replyService.register(vo); //댓글 저장 시도

        if(insertCount == 1){
            return new ResponseEntity<>("success", HttpStatus.OK); //200응답
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500에러
        }

    }

}
