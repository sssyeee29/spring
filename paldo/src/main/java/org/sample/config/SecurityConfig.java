package org.sample.config;

import org.sample.security.CustomUserDetailsService;
import org.sample.security.CustomLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // Spring Security 설정 클래스임을 명시
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize 등을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomLoginSuccessHandler successHandler;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 인증 관리자 설정 (UserDetailsService 사용)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    // 인가 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                // 메인, 로그인 페이지, 정적 리소스는 모두 허용
                .antMatchers("/", "/customLogin", "/resources/**", "/images/**").permitAll()
                // 게시판 관련 모든 요청은 로그인 필요
                .antMatchers("/board/**").authenticated()
                // 관리자만 접근 가능
                .antMatchers("/admin/**").hasRole("ADMIN")
                // 나머지 요청은 로그인 필요
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/customLogin") // 커스텀 로그인 페이지
                .successHandler(successHandler) // 로그인 성공 핸들러
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/") // 로그아웃 후 홈으로 이동
                .permitAll()
            .and()
            .exceptionHandling()
                .accessDeniedPage("/accessError"); // 권한 없을 때 이동할 페이지

        http.csrf().disable(); // CSRF 비활성화 (개발 중에는 편하게 하려고 설정, 실제 운영에선 다시 활성화할 것)
    }
}
