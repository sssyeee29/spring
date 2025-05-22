package org.sample.controller;
/*package org.zerock.controller;

import java.util.List;

import org.sample.domain.Criterial;
import org.sample.domain.ReplyPageDTO;
import org.sample.domain.ReplyVO;
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
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		log.info("ReplyVO : + vo");
		
		int insertCount = service.register(vo);
	    
		if(insertCount == 1) {
			//저장 성공
			return new ResponseEntity<>("success", HttpStatus.OK);
		}else {
			//저장실패
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/pages/{bno}/{page}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReplyPageDTO> getList(
			@PathVariable("bno") Long bno,
			@PathVariable("page") int page
			){
		log.info("getList........");
		
		Criterial cri = new Criterial(page, 10);
		
		return new ResponseEntity<>(service.getListPage(cri, bno),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{rno}",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		log.info("get........"+rno);

		return new ResponseEntity<ReplyVO>(service.get(rno), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		log.info("remove : "+rno);
		
		return service.remove(rno) == 1 ? new ResponseEntity<String>("success",HttpStatus.OK)
										: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
					value = "/{rno}",
					consumes = "application/json",
					produces = {MediaType.TEXT_PLAIN_VALUE})
	
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
		
		vo.setRno(rno);
		log.info("modify : " + rno);
		
		return service.modify(vo) == 1 ? new ResponseEntity<String>("success",HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
*/









