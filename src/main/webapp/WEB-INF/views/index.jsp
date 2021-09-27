<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <title>Accidents</title>
</head>
<body>
<div class="container pt-4">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th style="width: 20%;">Заголовок</th>
            <th style="width: 60%;">Описание</th>
            <th style="width: 20%;">Адрес</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${accidents}" var="accident">
            <tr>
                <th>${accident.name}</th>
                <th>${accident.text}</th>
                <th>${accident.address}</th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
