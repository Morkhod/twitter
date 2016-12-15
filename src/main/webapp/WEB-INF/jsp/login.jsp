<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
   <h1>Вход</h1>
      <c:if test="${param.error != null}">
          <div>
              Ошибка аутентификации.
              <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                Причина: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
              </c:if>
          </div>
      </c:if>
      <c:if test="${param.logout != null}">
          <div>
              Вы успешно вышли из системы.
          </div>
      </c:if>
   <form name='f' action="login" method='POST'>
      <table>
         <tr>
            <td>Логин:</td>
            <td><input type='text' name='username' value=''></td>
         </tr>
         <tr>
            <td>Пароль:</td>
            <td><input type='password' name='password' /></td>
         </tr>
         <tr>
            <td><input name="submit" type="submit" value="Войти" /></td>
         </tr>
      </table>
  </form>
  <a href="/reg">Зарегистрироваться</a>
</body>
</html>