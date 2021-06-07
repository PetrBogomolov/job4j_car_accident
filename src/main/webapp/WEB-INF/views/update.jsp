<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<body>
<form  action="<c:url value='/saveUpdate?id=${accident.id}'/>" method='POST'>
    <table>
        <tr>
            <td>Нарушитель:</td>
            <td><input type='text' name='name' value="<c:out value="${accident.name}"/>"></td>
            <td>Описание:</td>
            <td><input type='text' name='text' value="<c:out value="${accident.text}"/>"></td>
            <td>Адрес:</td>
            <td><input type='text' name='address' value="<c:out value="${accident.address}"/>"></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>
