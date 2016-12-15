<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<body>
	<a href="/logout">Выйти из системы</a>
	<a href="/">На главную</a>
	<h1>Все сообщения:</h1>
	<table border="1">
        <tr>
            <th>Номер</th>
            <th>Текст сообщения</th>
            <th>Автор</th>
        </tr>
        <c:forEach items="${allMsg}" var="msg">
        <tr>
            <td>
                <c:out value="${msg.getId()}" />
            </td>
            <td>
                <c:out value="${msg.getScreenedContent()}" />
            </td>
            <td>
                <c:out value="${msg.getAuthor()}" />
            </td>
        </tr>
        </c:forEach>
    </table>
	</body>
</html>