package com.hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.hello.jdbc.connection.ConnectionCons.*;

@Slf4j
public class DBConnectionUtil {
    // JDBC 표준 인터페이스가 제공해주는 ConnectionCons (java.sql.ConnectionCons)
    public static Connection getConnection() {
        try {
            // DriverManager 가 라이브러리에 있는 Driver 를 찾아서 DB 에 접근
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("get connection={}, class={}", connection, connection.getClass());
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
