package org.sample.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.sample.domain.BoardVO;
import org.sample.domain.Criteria;
import org.sample.domain.ImgPathVO;
import org.sample.domain.PageDTO;
import org.sample.domain.PostDTO;
import org.sample.domain.ProductVO;
import org.sample.domain.UserVO;
import org.sample.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j
public class BoardController {

    private final BoardService service;

    //get방식, 전체데이터를 뿌리는 역할
    @GetMapping("/list")
    public void list(Criteria cri, Model model) {
        log.info("list... " + cri);

        List<PostDTO> postList = service.getPostList(cri);  // PostDTO 리스트 조회
        model.addAttribute("list", postList);
        model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
    }
    
    // 게시글 작성 페이지 진입
    @GetMapping("/register")
    public void register() {
    }

    // 게시글 등록 (POST 방식, 하나의 PostDTO를 서버에 전달)
    @PostMapping("/register")
    public String register(@ModelAttribute PostDTO postDTO, RedirectAttributes rttr) {
        log.info("register...");
        
        service.register(
        		postDTO.getBoard(),
        		postDTO.getUser(),
        		postDTO.getProduct(),
        		postDTO.getImgPaths()
        		);
        
        rttr.addFlashAttribute("result", postDTO.getBoard().getBoardid());
        return "redirect:/board/list";
    }

    // 상세보기
    @GetMapping("/get")
    public void get(@RequestParam("boardid") Long boardid, @ModelAttribute("cri") Criteria cri, Model model) {
        log.info("get...");
        
        BoardVO board = service.get(boardid);  // 상세보기는 BoardVO만 불러와도 충분하다면 이렇게
        model.addAttribute("board", board);
    }

    // 수정폼 요청
    @GetMapping("/modify")
    public void modify(@RequestParam("boardid") Long boardid, @ModelAttribute("cri") Criteria cri, Model model) {
        log.info("modify...");
        
        PostDTO postDTO = service.getPostList(cri);  // 수정은 PostDTO 전체 불러오기
        model.addAttribute("postDTO", postDTO);
    }
    
    
    
    
    
    
//    // 게시글 상세 보기 및 수정 페이지 진입
//    @GetMapping({"/get", "/modify"})
//    public void get(@RequestParam("boardid") Long boardid, @ModelAttribute("cri") Criteria cri, Model model) {
//        log.info("get or modify...");
//       
//        BoardVO board = service.get(boardid);
//        model.addAttribute("board", board);
//    }

    // 게시글 삭제
    @PostMapping("/remove")
    public String remove(@RequestParam("boardid") Long boardid, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
        log.info("remove...");
        
        boolean removed = service.remove(boardid);

        if (removed) {
            rttr.addFlashAttribute("result", "삭제 성공했습니다.");
        } else {
            rttr.addFlashAttribute("result", "삭제에 실패했습니다.");
        }
        
        // 페이지 및 검색 조건 유지 
        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());
        
        return "redirect:/board/list";
    }

    // 게시글 수정
    @PostMapping("/modify")
    public String modify(@ModelAttribute BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
        log.info("modify...");
        
        
        if (service.modify(board)) {
            rttr.addFlashAttribute("result", "수정 성공했습니다.");
        } else {
            rttr.addFlashAttribute("result", "수정 실패했습니다.");
        }
        
        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());
        
        return "redirect:/board/list";
    }
}











