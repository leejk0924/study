package com.hello.jdbc.repository;

import com.hello.jdbc.connection.DBConnectionUtil;
import com.hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * JDBC - DriverManager 사용
 */
@Slf4j
public class MemberRepositoryV0 {
    public Member save(Member member) throws SQLException {

        String sql = "insert into member(member_id, money) values (?,?)";

        Connection con = null;
        // PreparedStatement 로 sql 인젝션을 막을 수 있다. (구문이 단순 문자로 입력됨-> 파라미터 바인딩 덕분에) [ex : value("select..."]
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            // 쿼리 날리기   // 영향 받은 row 수만큼 반환해 준다.
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally {
            // pstmt 와 con 을 close 해주지 않으면 계속해서 연결이 유지되어 있다.
            // Exception 발생할 경우 con 이 호출이 안될 수 있다.
            close(con, pstmt, null);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {

            try {
                rs.close();
            } catch (SQLException e) {
                log.info("error", e);
            }

        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
    }

    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
