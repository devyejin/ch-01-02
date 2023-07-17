<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yejin
  Date: 2023-07-16
  Time: 오전 1:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo list </title>
</head>
<body>
<h1>Todo List</h1>

<ul>
    <c:forEach items="${dtoList}" var="dto"> <!-- for문 돌면서 dtoList에 담긴걸 dto란 이름으로 꺼낸다 -->
        <li>
            <span><a href="/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
            <span>${dto.title}</span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished? "DONE" : "NOT YET"}</span>
        </li>
    </c:forEach>
</ul>
</body>
</html>
