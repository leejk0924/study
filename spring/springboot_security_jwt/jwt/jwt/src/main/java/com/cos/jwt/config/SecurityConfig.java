package com.cos.jwt.config;

import com.cos.jwt.JwtApplication;
import com.cos.jwt.config.auth.jwt.JwtAuthenticationFilter;
import com.cos.jwt.config.auth.jwt.JwtAuthorizationFilter;
import com.cos.jwt.filter.MyFilter3;
import com.cos.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;


// JWT 사용시 기본 세팅
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(new MyFilter3(), BasicAuthenticationFilter.class); // 스프링 시큐리티보다 먼저 필터 적용
//        http.addFilterAfter(new MyFilter3(), BasicAuthenticationFilter.class);
        http.csrf().disable();
        // 세션을 사용하지 않는 서버를 만든다는 코드 (stateless 서버 만든다는 뜻)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)      // @CrossOrigin (인증이 없을 경우), 시큐리티 필터에 등록(인증이 있을 경우)
                // http 로그인방식 사용 X
                .formLogin().disable()
                // HTTP header 에 Authorization 을 담아서 요청하는 방식이 httpBasic 방식 (인증 요청시 사용되는 ID, PW 가 평문으로 보내짐)
                // bearer Authentication 방식은 ID와 PW 으로 토큰을 만들어 전송하는 방식으로 httpBasic 보다는 안전하다 [bearer 방식을 사용하려면 httpBasic()을 disable 해야한다.]
                .httpBasic().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))   // 필터에 다시 등록    // AuthenticationManager 을 보내줘야함
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
    }
}
/**
 * CSRF : Cross site Request forgery 로 사이트간 위조 요청으로, 사용자가 의도치 않은 위조요청을 보내는 것을 의미
 */