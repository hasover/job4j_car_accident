<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>Users</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <th>${user}</th>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
