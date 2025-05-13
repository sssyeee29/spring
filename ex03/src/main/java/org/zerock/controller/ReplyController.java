package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criterial;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequiredArgsConstructor
@Log4j
@RequestMapping("/replies")
public class ReplyController {
	
	private final ReplyService service;
	
	@PostMapping(value = "/new")
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){ //@RequestBody -> json형태로 받아주겠다.
		log.info("ReplyVO : + vo");
		int insertCount = service.register(vo);
		
		if(insertCount == 1) {
			//저장 성공
			return new ResponseEntity<String>("success", HttpStatus.OK); //성공하면 성공코드 200번대 전송
		}else {  
			//저장 실패
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); //실패하면 500번대 전송 
		}
	}
	
	@GetMapping(value = "/pages/{bno}/{page}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyVO>> getList(
			@PathVariable("bno") Long bno,
			@PathVariable("page") int page
			){
		log.info("getList.........");
		
		Criterial cri = new Criterial(page, 10);
		
		return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
	}
	
	//단건데이터
	@GetMapping(value = "/{rno}",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		log.info("get............" + rno);
		
		return new ResponseEntity<ReplyVO>(service.get(rno), HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/{rno}", 
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		log.info("remove : " + rno);
		
		return service.remove(rno) == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) // 성공코드 200
										: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); //실패코드 500
	}
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},  //put이랑 patch의 요청을 둘 다 받기위해서 @RequestMapping을 씀
							value = "/{rno}",
							consumes = "application/json", //값은 json으로 넘겨줌
							produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
		
		vo.setRno(rno);
		log.info("modify : " + rno);
		
		return service.modify(vo) == 1 ? new ResponseEntity<String>("success", HttpStatus.OK) // 성공코드 200
										: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); //실패코드 500
	}
		
	
	
}
