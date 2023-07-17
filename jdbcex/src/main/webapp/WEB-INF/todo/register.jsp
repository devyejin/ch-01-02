<%--
  Created by IntelliJ IDEA.
  User: yejin
  Date: 2023-07-16
  Time: 오전 2:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo 등록 페이지</title>
</head>
<body>

<form action="/todo/register" method="post">
  <div>
    <input type="text" name="title" placeholder="INSERT TITLE">
  </div>
  <div>
    <input type="date" name="dueDate">
  </div>
  <div>
    <button type="reset">RESET</button>
    <button type="submit">REGISTER</button>
  </div>

</form>
</body>
</html>
