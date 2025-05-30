package org.zerock.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j 
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {

		log.warn("Login Success");
		
		List<String> roleNames = new ArrayList<String>();
		
		auth.getAuthorities().forEach(authority -> { //admin안에는 권한이 두개가 있으니까 람다식을 이용해서 보기위함
			roleNames.add(authority.getAuthority());
		});
		
		log.warn("ROLE NAMES : " + roleNames);
		
		// 로그인 정보 확인 후 각각의 맞는 권한 페이지로 바로 이동해서 보여주기 위함.
		if(roleNames.contains("ROLE_ADMIN")) { 
			response.sendRedirect("/sample/admin");
			return;
		}else if(roleNames.contains("ROLE_MEMBER")){
			response.sendRedirect("/sample/member");
			return;
		}
		
		response.sendRedirect("/"); 
	}

}
