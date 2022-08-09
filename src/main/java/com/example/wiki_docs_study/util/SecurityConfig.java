package com.example.wiki_docs_study.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

/**
 * 로그인을 안해도 돌아갈수 있게 한다,
 *
 *
 * */
@Configuration // 환경설정 파일 설정
@EnableWebSecurity // 모든 요청 url 을 스프링시큐리티에 제어를 받도록 반드는 어노테이션
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 모든 인증되지 않은 요청을 허락한다는 의미
        http.authorizeRequests().antMatchers("/**").permitAll()
                .and()
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
        return http.build();
    }
}