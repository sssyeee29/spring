package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice //서버에 문제가 터지면 작동함 (평소에는 작동안됨)
@Log4j
public class CommonExceptionAdvice {
	
	@ExceptionHandler(Exception.class) // 최상이 exception이라서 문제 터지면 얘가 다 받아줌
	public String except2(Exception ex, Model model) {
		log.error("Exception............." + ex.getMessage());
		model.addAttribute("exception", ex);
		log.error(model);
		return "error_page2";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class) // 최상이 exception이라서 문제 터지면 얘가 다 받아줌
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handler404(NoHandlerFoundException ex) {
		return "custom404";
	}
	
	
}
