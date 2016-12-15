<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<a href="/logout">Выйти из системы</a>
<a href="/">На главную</a>
<form action="/showMessageData" method="get">
<input type="number" name="strId">
<button type="submit">Показать сообщение</button>
</form>
${errorMessage}