<%--
  Created by IntelliJ IDEA.
  User: yejin
  Date: 2023-07-16
  Time: 오전 2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read page</title>
</head>
<body>
<div>
    <input type="text" name="tno" value="${dto.tno}" readonly>
</div>
<div>
    <input type="text" name="title" value="${dto.title}" readonly>
</div>
<div>
    <input type="date" name="dueDate" value="${dto.dueDate}" >
</div>
<%--chekced 의 경우, 그냥 속성명으로 표기--%>
<div>
    <input type="checkbox" name="finished" ${dto.finished ? "checked" : ""} readonly>
</div>

<div>
    <a href="/todo/modify?tno=${dto.tno}">Modify/Remove</a>
    <a href="/todo/list">List</a>
</div>
</body>
</html>
