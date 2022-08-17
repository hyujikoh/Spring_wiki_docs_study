package com.example.wiki_docs_study.util;

import com.example.wiki_docs_study.src.user.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpSession;

/**
 * 로그인을 안해도 돌아갈수 있게 한다,
 *
 *
 * */

@RequiredArgsConstructor
@Configuration // 환경설정 파일 설정
@EnableGlobalMethodSecurity(prePostEnabled = true) // 모든 요청 url 을 스프링시큐리티에 제어를 받도록 반드는 어노테이션
public class SecurityConfig {
    private final UserSecurityService userSecurityService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {



        // 모든 인증되지 않은 요청을 허락한다는 의미
        http.authorizeRequests().antMatchers("/**").permitAll()
                .and()
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()
                .formLogin()
                .loginPage("/user/login")
                .successHandler((request, response, authentication) -> {
                    HttpSession session = request.getSession(false);
                    session.setMaxInactiveInterval(18000);
                    session.setAttribute("name",userSecurityService.name());
                    session.setAttribute("pwd",userSecurityService.pwd());
                    System.out.println(session.getAttribute("name"));
                    System.out.println(session.getAttribute("pwd"));
                    response.sendRedirect("/");
                })
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessHandler((request, response, authentication) -> {
                    HttpSession session = request.getSession();
                    session.invalidate();
                    response.sendRedirect("/");
                })
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}