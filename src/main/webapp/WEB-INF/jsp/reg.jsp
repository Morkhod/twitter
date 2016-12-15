<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
   <a href="/">На главную</a>
   <h1>Регистрация</h1>
   ${errorMessage}
   <form name='r' action="regin" method='POST'>
      <table>
        <tr>
           <td>Логин:</td>
           <td><input type='text' name='login'></td>
        </tr>
        <tr>
           <td>Пароль:</td>
           <td><input type='password' name='password' /></td>
        </tr>
        <tr>
           <td><input name="submit" type="submit" value="Зарегистрироваться" /></td>
        </tr>
      </table>
  </form>
</body>
</html>