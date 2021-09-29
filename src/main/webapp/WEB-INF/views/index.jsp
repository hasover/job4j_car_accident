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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Accidents</title>
</head>
<body>
<div class="container pt-4">
    <a href="<c:url value='/create'/>">Добавить инцидент</a>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th style="width: 5%"><i class="fa fa-edit mr-3"></i></th>
            <th style="width: 15%;">Заголовок</th>
            <th style="width: 20%;">Описание</th>
            <th style="width: 20%;">Адрес</th>
            <th style="width: 20%;">Тип</th>
            <th style="width: 20%;">Статьи</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${accidents}" var="accident">
            <tr>
                <th>
                    <a href="<c:url value="/edit?id=${accident.id}"/>">
                        <i class="fa fa-edit mr-3"></i>
                    </a>
                </th>
                <th>${accident.name}</th>
                <th>${accident.text}</th>
                <th>${accident.address}</th>
                <th>${accident.type.name}</th>
                <th>
                    <ul>
                    <c:forEach items="${accident.rules}" var="rule">
                        <li>${rule.name}</li>
                    </c:forEach>
                    </ul>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
