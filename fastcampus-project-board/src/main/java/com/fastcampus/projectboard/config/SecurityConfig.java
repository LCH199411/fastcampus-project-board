package com.fastcampus.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig { // extends WebSecurityConfigurerAdapter 상속받는건 컴포넌트 스타일로 바뀌어서 사용 못함


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        http
                .authorizeHttpRequests((auth) -> auth.anyRequest().permitAll())
                .formLogin();
        return http.build();
       */
        return http
                .authorizeHttpRequests((auth) -> auth.anyRequest().permitAll())
                .formLogin().and().build();


    }


}
