<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css">
    <title>Accident</title>
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th>Номер</th>
        <th>Нарушитель</th>
        <th>Описание</th>
        <th>Адрес</th>
        <th>Действия</th>
    </tr>
    </thead>
    <a href="<c:url value='/create'/>">Добавить инцидент</a>
    <tbody>
    <c:forEach items="${intruders}" var="accident">
        <tr>
            <th><c:out value="${accident.id}"/></th>
            <th><c:out value="${accident.name}"/></th>
            <th class="table-danger"><c:out value="${accident.text}"/></th>
            <th><c:out value="${accident.address}"/></th>
            <th> <a href="<c:url value='/update'/>"><i class="bi bi-pencil-square"></i></a></th>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
