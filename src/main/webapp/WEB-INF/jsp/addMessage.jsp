<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<a href="/logout">Выйти из системы</a>
<a href="/">На главную</a>
<form action='addMessageData' method='post'>
<input type='text' id='data' name='data' size='30' maxlength='255'>
<button type='submit'>Отправить сообщение</button>
</form>
${errorMessage}