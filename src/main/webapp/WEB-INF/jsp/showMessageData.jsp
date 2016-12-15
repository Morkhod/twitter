<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<body>
	<a href="/logout">Выйти из системы</a>
    <a href="/">На главную</a>
        <br>
	    Номер сообщения: ${id}
	    <br>
	    Текст сообщения: ${content}
	    <br>
	    Автор сообщения: ${author}
	</body>
</html>