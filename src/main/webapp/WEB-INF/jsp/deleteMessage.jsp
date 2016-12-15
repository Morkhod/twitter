<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<a href="/logout">Выйти из системы</a>
<a href="/">На главную</a>
<form action="deleteMessageById" method='get'>
<input type='number' id='strId' name='strId' size='30'>
<button type="submit">Удалить сообщение</button>
</form>
${errorMessage}