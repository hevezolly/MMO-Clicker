<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" errorPage="loginError" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Войдите в свой аккаунт</title>
</head>

<body>
<sec:authorize access="isAuthenticated()">
  <% response.sendRedirect("/"); %>
</sec:authorize>
<c:if test="${not empty param.error}">Неправильный логин или пароль</c:if>
<div>
  <form method="POST" action="/login">
    <h2>Вход в систему</h2>
    <div>
      <input name="username" type="text" placeholder="Логин"
             autofocus="true"/>
      <input name="password" type="password" placeholder="Пароль"/>
      <button type="submit">Вход</button>
      <h4><a href="/registration">Зарегистрироваться</a></h4>
      <h4><a href="/">Главная</a></h4>
    </div>
  </form>
</div>

</body>
</html>