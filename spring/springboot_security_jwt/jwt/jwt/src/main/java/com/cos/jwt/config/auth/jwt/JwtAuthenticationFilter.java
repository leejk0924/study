package com.cos.jwt.config.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.jwt.config.auth.PrincipalDetails;
import com.cos.jwt.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

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
        try {
//            // request.getInputStream() 안에 username 과 password 가 들어있다.
//            BufferedReader br = request.getReader();
//            String input = null;
//            while ((input = br.readLine()) != null) {
//                System.out.println(input);
//            }
//            System.out.println(request.getInputStream());
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println(user);

            // PrincipalDetailsService 의 loadUserByUsername() 함수가 실행된 후 정상이면 authentication 이 리턴됨.
            // DB 에 있는 username 과 password 가 일치한다.
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);  // 로그인한 정보가 담긴다

            // authentication 객체가 session 영역에 저장됨. => 로그인이 되었다는 뜻뜻
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("로그인 완료됨"+principalDetails.getUser().getUsername());       // 로그인이 정상적으로 되었다는 뜻
            // authentication 객체가 session 영역에 저장을 해야하고 그 방법이 return 해주면됨
            // return 의 이유는 권한 관리를 security 가 대신 해주기 떄문에 편하려고 하는거임
            // 굳이 JWT 토큰을 사용하면서 세션을 만들 이유가 없음. 근데 단지 권한 처리 때문에 session 넣어 준다.
            return authentication;

        } catch (IOException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }

        System.out.println("===================================================");

        // 2. 정상인지 로그인 시도를 해본다.  authenticationManager 로 로그인 시도를 하면!!
        // PrincipalDetailsService 가 호출 loadUserByUsername() 함수 실행됨

        // 3. PrincipalDetails 를 세션에 담고 (버전 관리를 위해서)
        // 4. JWT 토큰을 만들어서 응답해주면 된다.
        return null;
    }
    // attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행된다.
    // JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해주면 됨.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication 실행됨 : 인증이 완료되었다는 뜻");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // HMAC512 암호 방식
        String jwtToken = JWT.create()
//                .withSubject(principalDetails.getUsername())
                .withSubject("cos토큰")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000 * 30)))    // 만료시간 설정 (30분 설정)
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512("cos"));

        response.addHeader("Authorization", "Bearer " + jwtToken);  // "Bearer " <- 한칸 띄어쓰기 해줘야함
    }
}
