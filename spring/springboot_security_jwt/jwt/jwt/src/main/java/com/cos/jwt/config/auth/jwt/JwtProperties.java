package com.cos.jwt.config.auth.jwt;

// 이와 같이 Properties 를 만들어서 사용하면 실수 할 확률도 줄어들고 비밀 키 등을 감출 수 있다.
public interface JwtProperties {
    String SECRET = "cos";  // 우리 서버만 알고 있는 비밀 값
    int EXPIRATION_TIME = 60000 * 10; // 10일 (1/10000초)
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
