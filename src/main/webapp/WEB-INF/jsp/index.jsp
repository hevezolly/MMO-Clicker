<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
  <title>Главная</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<div>
  <h3>${pageContext.request.userPrincipal.name}</h3>
  <sec:authorize access="!isAuthenticated()">
    <h4><a href="/login">Войти</a></h4>
    <h4><a href="/registration">Зарегистрироваться</a></h4>
  </sec:authorize>
  <sec:authorize access="isAuthenticated()">
    <p>click count:</p>
    <p>${click_count}</p>
    <form method="POST">
      <button type="submit" name="click" value="clicked">Click</button>
    </form>
    <h4><a href="/create_team">Создать команду</a></h4>
    <h4><a href="/join_team">присоедениться к команде</a></h4>
    <h4><a href="/team">моя команда</a></h4>
    <h4><a href="/logout">Выйти</a></h4>
  </sec:authorize>
</div>
</body>
</html>