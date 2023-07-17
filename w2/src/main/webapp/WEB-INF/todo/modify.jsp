<%--
  Created by IntelliJ IDEA.
  User: yejin
  Date: 2023-07-16
  Time: 오전 2:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo modify page</title>
</head>
<body>
<%-- 수정 --%>
<form action="/todo/modify" method="post" id="form1">
    <div>
        <input type="text" name="tno" value="${dto.tno}" readonly>
    </div>
    <div>
        <input type="text" name="title" value="${dto.title}">
    </div>
    <div>
        <input type="date" name="dueDate" value="${dto.dueDate}" >
    </div>
    <div>
        <input type="checkbox" name="finished" ${dto.finished ? "checked" : ""} >
    </div>

    <div>
        <button type="submit">Modify</button>
    </div>
</form>

<%--삭제 삭제의 경우도 data에 변화가 발생하므로 POST요청임--%>
<form action="/todo/remove" method="post" id="form2">
    <!-- 삭제의 경우, hidden 태그로 속성값 보냄 -->
    <input type="hidden" name="tno" value="${dto.tno}" readonly>
    <div>
        <button type="submit">REMOVE</button>
    </div>
</form>
</body>
</html>
