<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
<%--    <li>id =<%=((Member)request.getAttribute("member")).getId()%></li>--%>
    <li>id=${member.id}</li>
<%--    <li>username =<%=((Member)request.getAttribute("member")).getUsername()%></li>--%>
    <li>username=${member.username}</li>
<%--    <li>age =<%=((Member)request.getAttribute("member")).getAge()%></li>--%>
    <li>age=${member.age}</li>
<%--조회할 떄는 자동으로 get, 수정할 떄는 set으로 된다.--%>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
