package com.example.security.repository;

import com.example.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD 함수를 JpaRepository 가 들고 있음.
// @Repository 라는 어노테이션이 없어도 IoC된다. 이유는 JpaRepository 를 상속했기 때문에
public interface UserRepository extends JpaRepository<User, Integer> {
    // findBy 규칙 -> Username 문법
    // select * from user where username = 1?
    public User findByUsername(String username);    // JPA query method


}
