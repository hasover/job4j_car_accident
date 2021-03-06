<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактирование</title>
</head>
<body>
<form action="<c:url value='/save?id=${accident.id}&type.id=${accident.type.id}'/>" method="POST">
    <table>
        <tr>
            <td>Название:</td>
            <td><input type='text' name='name' value="${accident.name}"></td>
        </tr>
        <tr>
            <td>Описание:</td>
            <td><textarea name='text' rows="3">${accident.text}</textarea></td>
        </tr>
        <tr>
            <td>Адрес:</td>
            <td><input type='text' name='address' value="${accident.address}"></td>
        </tr>
        <tr hidden>
            <td>
                <select name="rIds" multiple>
                    <c:forEach var="rule" items="${accident.rules}" >
                        <option selected value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>


</body>
</html>
