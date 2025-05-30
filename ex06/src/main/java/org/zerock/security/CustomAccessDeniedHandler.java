package org.zerock.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.log4j.Log4j;

//사용자가 **접근 권한이 없는 페이지에 접근하려고 할 때 발생하는 예외(403 forbidden)**를 처리하는 클래스 
//보통 접근 거부 시 사용자에게 메세지를 보여주거나, 특정 페이지로 리다이렉트 하는 역할을 함
//즉, "접근 불가 경고를 처리하는 담당자"

@Log4j			//직접 만든거 							//스프링에서 만든거 
public class CustomAccessDeniedHandler implements AccessDeniedHandler{ //error가 터지면 여기 메소드(직접 만든거)를 쓰겠다.
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		log.error("Access Denied Handler....");
		log.error("Redirect");
		
		response.sendRedirect("/accessError");
	}

}
