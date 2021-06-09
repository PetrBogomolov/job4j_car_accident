<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<body>
<form action="<c:url value='/saveUpdate?id=${accident.id}'/>" method='POST'>
    <table>
        <tr>
            <td>Нарушитель:</td>
            <td><input type='text' name='name' value="<c:out value="${accident.name}"/>"></td>
        </tr>
        <tr>
            <td>Тип нарушения:</td>
            <td>
                <select name="type.id">
                    <c:forEach var="type" items="${types}">
                        <option value=<c:out value="${type.id}"/>><c:out value="${type.name}"/></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Статья КоАП РФ:</td>
            <td>
                <select name="rIds" multiple>
                    <c:forEach var="rule" items="${rules}">
                        <option value=<c:out value="${rule.id}"/>><c:out value="${rule.name}"/></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Описание:</td>
            <td><input type='text' name='text' value="<c:out value="${accident.text}"/>"></td>
        </tr>
        <tr>
            <td>Адрес:</td>
            <td><input type='text' name='address' value="<c:out value="${accident.address}"/>"></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить"/></td>
        </tr>
    </table>
</form>
</body>
</html>
