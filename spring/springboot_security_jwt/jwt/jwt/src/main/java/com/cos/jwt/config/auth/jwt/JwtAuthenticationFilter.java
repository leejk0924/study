package com.cos.jwt.config.auth.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 가 있음
// login 요청해서 username, password 전송하면 (post)
// UsernamePasswordAuthenticationFilter 동작을 함  (현재 동작하지 않는 이유는 securityConfig 에서 formLogin().disable() 했기 떄문에)
// 다시 등록하는 방법은 securityConfig 에 다시 등록해준다.
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter : 로그인 시도중");

        // 1.  username, password 받아서
        // 2. 정상인지 로그인 시도를 해본다.  authenticationManager 로 로그인 시도를 하면!!
        // PrincipalDetailsService 가 호출 loadUserByUsername() 함수 실행됨

        // 3. PrincipalDetails 를 세션에 담고 (버전 관리를 위해서)
        // 4. JWT 토큰을 만들어서 응답해주면 된다.
        return super.attemptAuthentication(request, response);
    }
}
