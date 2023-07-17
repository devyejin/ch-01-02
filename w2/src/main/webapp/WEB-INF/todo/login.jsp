<%--
  Created by IntelliJ IDEA.
  User: yejin
  Date: 2023-07-16
  Time: 오후 1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

<c:if test="${param.result == 'error'}">
    <h1>로그인 에러</h1>
</c:if>

<form action="/login" method="post">
    <input type="text" name="mid"/>
    <input type="password" name="mpw"/>
    <input type="checkbox" name="auto"/> <!-- checkbox 체크하면 auto 값이 on으로 넘어감 -->
    <button type="submit">LOGIN</button>
</form>
</body>
</html>
