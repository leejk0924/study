package com.example.security.config.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인 진행이 완료가 되면 session 을 만들어준다. (Security ContextHolder 에 저장)
// 오브젝트 타입 => Authentication 타입 객체
// Authentication 안에 User 정보가 있어야됨
// User 오브젝트 타입 => UserDetails 타입 객체

import com.example.security.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// Security Session => Authentication => UserDetails(PrincipalDetails)
public class PrincipalDetails implements UserDetails {

    private User user;  // 컴포지션

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 User 의 권한을 리턴하는 곳!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 우리 사이트! 1년 동안 회원이 로그인을 안하면 휴먼 계정으로 하기로함
        // 현재시간 - 로긴시간 => 1년 초과하면 return 값 false
        return true;
    }
}
