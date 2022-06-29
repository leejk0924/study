<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // request, response 사용 가능 (JSP 도 결국에는 서블릿으로 자동으로 변환되어 사용되기 때문에 사용 가능 : 문법상 지원)
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSave.service");
    String username = request.getParameter("username");

    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

%>

<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id =<%=member.getId()%></li>
    <li>username =<%=member.getUsername()%></li>
    <li>age =<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
