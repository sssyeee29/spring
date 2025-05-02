package org.zerock.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;

import lombok.Builder;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {

	/*
	 * 반환타입이 void일 때 (@RequestMapping("/sample")이 부분에서 /sample이 폴더 역할을 하는거..진짜로 있는건아니지만)
	 *  / : /view/sample.jsp 를 찾아감
	 *  /basic : /view/sample/basic.jsp 를 찾아감
	 *  /basicOnlyGet : /view/sample/basicOnlyGet.jsp
	 *  반환타입이 String이면 반환타입 return부분에 쓰여진게 jsp이름 ex) return "aaa"; -> //view/aaa.jsp 이런식 
	 *
	 * 리턴이 void인 경우 view화면은 경로명으로 찾는다 
	 * /view/sample.jsp 
	 */
	
	//view/sample.jsp
	@RequestMapping("/") 
	public void basic() { 
		log.info("basic..........");
	}
	
	// view/sample/basic.jsp
	@RequestMapping(value="/basic", method= {
			RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get......");
	}
	
	// view/sample/basicOnlyGet.jsp
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get......");
		//view/sample/basicOnlyGet.jsp를 찾아감 // void면 mapping된 이름이 jsp이름이 됨
	}
	
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto);
		return "ex01"; // view/ex01.jsp를 찾아감
	}
	
	//localhost:8080/sample/ex02?name=kim&age=20 ==> 쿼리스트링형식으로 문자열로 받아주는데 
	//	int age가 되는이유는 알아서 Integer.parseInt를 해주기때문에 가능 
	@GetMapping("/ex02") //model을 써서 값을 담아줘야지 jsp화면에 값이 뜬다
	public String ex02(@RequestParam String name, @RequestParam int age, Model model) {
		log.info(name);
		log.info(age);
		model.addAttribute("name", name);
		model.addAttribute("age",age);
		return "ex02"; //ex02.jsp를 찾아감
	}
	
//	@InitBinder // 화면에서 날짜를 입력받을거면 
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//	}
	
	//localhost:8080/sample/ex03?title=spring&dueDate=2025-5-1
	@GetMapping("/ex03")
	public String ex03(TodoDTO todoDTO) {
		log.info(todoDTO);
		return "ex03"; 
	}
	
/*	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, int page, Model model) {
		log.info(dto);
		log.info(page);
		model.addAttribute("page", page);
		model.addAttribute("sample", dto); //객체나 기본자료형이랑 둘 다 model에 담아서 넘기기 -> 객체는 안담아도 넘어가긴함. 기본자료형이 안넘어가는거 
		return "sample/ex04";
	}*/
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto) {
		log.info(dto);
		return "sample/ex04";
	}
	
	@GetMapping("/rttr") //딱 1번만 값이 전달됨 -> 새로고침하면 값이 전달이 안됨.(값이 없음) 
	//RedirectAttributes rttr : 리다이렉트할 때 데이터를 1회성으로 잠깐 담아두는 객체 
	public String rttr(SampleDTO dto, RedirectAttributes rttr) { 
		rttr.addFlashAttribute("sampleDTO", dto);
		return "redirect:/sample/ex04";
	}
	
	@GetMapping("/ex06")
	//@ResponseBody => 변환타입은 json으로 변환
	public @ResponseBody SampleDTO ex06() { //pom파일에서 json 설정후 @ResponseBody 쓰면 json형식으로 전달됨
		log.info("/ex06...........");
	
		/*	SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		return dto; */
	
		return SampleDTO.builder()
				.name("park")
				.age(30)
				.build();
	
		
	}
	
	@GetMapping("/ex06_1")
	//@RequestBody => json을 java 객체로 변환
	public String ex06_1(@RequestBody SampleDTO dto) { 
		log.info("/ex06_1...........");
		log.info(dto);
		return "/sample/ex06_1";
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){ //문자열을 반환 
		String msg = "{\"name\": \"홍길동\"}";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "applicationi/json;charset=utf-8");
		return new ResponseEntity<String>(msg, headers, HttpStatus.ACCEPTED);
	}
}


