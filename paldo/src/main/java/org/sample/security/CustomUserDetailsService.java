package org.sample.security;

import org.sample.security.domain.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.sample.domain.MemberVO;
import org.sample.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberMapper memberMapper; //mapper통해서 db가서 값을 가져오기 위해서 쓴 코드 
	
	@Override							 //AuthenticationProvider가 전달한 username을 받음 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		log.warn("Load User By UserName :" + username);
		
		MemberVO vo = memberMapper.read(username);
		
		return vo == null ? null : new CustomUser(vo); //null값이면 null반환, 아니면 new CustomUser(vo)에 넣겠다  
	}

}
