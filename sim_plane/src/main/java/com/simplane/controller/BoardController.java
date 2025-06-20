package com.simplane.controller;

import com.simplane.domain.BoardVO;
import com.simplane.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Writer;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

//    @GetMapping("/register")
//    public String register(){
//
//    }

    @PostMapping("/register")
    public String register(BoardVO boardVO, RedirectAttributes rttr) {
        log.info("register.....");
        boardService.register(boardVO);
        rttr.addFlashAttribute("result", boardVO.getBoardid());
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("boardid") Long boardid, RedirectAttributes rttr, Writer writer) {
        log.info("remove..." + boardid);
//        log.info("remove...writer..." + writer);

//        if (boardService.remove(boardid)) {
//            rttr.addFlashAttribute("result", "삭제를 성공했습니다.");
//        }
        return "redirect:/board/list";
    }


}






















