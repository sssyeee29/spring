package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor //주입하기위해서 
@Log4j
public class BoardController {
	private final BoardService service;
	
	@GetMapping("/list")
	public void list(Criterial cri, Model model) {
		log.info("list.................." + cri);
		
		
		List<BoardVO> list = service.getList(cri);
		model.addAttribute("list", list); 
		
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register") //post는 항상 redirect로 해줘야함 !! (멱등성) post -> redirect -> get 이 순서 (PRG패턴)
	public String register(BoardVO board, RedirectAttributes rttr) { //모든항목이 필요하니까 (괄호안에 parameter에 맞는 갑을 넣으면 됨)
		log.info("register...........");		
		service.register(board); //등록 후
		rttr.addFlashAttribute("result", board.getBno()); //한번만 보여주기위해서 
		
		return "redirect:/board/list"; //입력하고 페이지이동이 되어야하니까 -> 화면전환이 안되면
										//새로고침하면 계속 추가되니까 반드시 화면전환이 있어야함(DB에 변화가 일어나면 반드시 화면전환이 필요함)
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam Long bno,@ModelAttribute("cri") Criterial cri, Model model) {
		log.info("get...modify.......");
		
		model.addAttribute("board", service.get(bno));//db에서 bno에 해당하는 번호값을 가져와서 board에 담아서 해당 jsp로 이동
	}
	
	@PostMapping("/remove")
	public String remove(Long bno,@ModelAttribute("cri") Criterial cri, RedirectAttributes rttr) {
		log.info("remove..........");
		
		if(service.remove(bno)) { //삭제하고 
		rttr.addFlashAttribute("result", " 삭제 성공했습니다.");
		}

		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	
	@PostMapping("/modify")
	public String modify(BoardVO board,@ModelAttribute("cri") Criterial cri, RedirectAttributes rttr) { //책에서 모든항목을 가져온다고 해서(표보면 나와있음) 
		log.info("modify..........");
		
		if(service.modify(board)) {
		
		rttr.addFlashAttribute("result", " 수정 성공했습니다.");
		}
		
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/board/list";
	}
}














