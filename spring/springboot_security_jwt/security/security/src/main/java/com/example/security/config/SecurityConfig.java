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
                .antMatchers("/user/**").authenticated()    // /user/** 일 경우 인증 요청      // 인증만 되면 들어갈 수 있는 주소
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")  // ROLE_ADMIN 이거나 ROLE_MANAGER 일 경우에만 접속
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")       // admin 권한일 경우만 접속 가능
                .anyRequest().permitAll()      // 다른 요청은 다 허용
                // 권한이 없지만 권한있는 페이지에 접속할 경우 login 페이지로 이동
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .usernameParameter("username")
                .loginProcessingUrl("/login")  // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
                .defaultSuccessUrl("/");    // /loginForm url 을 통해 접속했을 경우 / 경로로 보내주지만 특정 url (/user 같이) 입력했을 경우 요청했던 url 로 return 해준다.
    }
}
