<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <%
        for (Member member : members) {
            // out 예약어
            out.write("    <tr>\n");
            out.write("        <td>" + member.getId() + "</td>\n");
            out.write("        <td>" + member.getUsername() + "</td>\n");
            out.write("        <td>" + member.getAge() + "</td>\n");
            out.write("    </tr>\n");
        }
    %>
    </tbody>
</table>


</body>
</html>
