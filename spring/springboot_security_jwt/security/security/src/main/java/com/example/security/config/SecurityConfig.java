package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity  // 활성화 : 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해 준다.
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()    // /user/** 일 경우 인증 요청
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")  // ROLE_ADMIN 이거나 ROLE_MANAGER 일 경우에만 접속
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")       // admin 권한일 경우만 접속 가능
                .anyRequest().permitAll()      // 다른 요청은 다 허용
                // 권한이 없지만 권한있는 페이지에 접속할 경우 login 페이지로 이동
                .and()
                .formLogin()
                .loginPage("/loginForm");
    }
}
