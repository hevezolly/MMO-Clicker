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
    <p>Количество кликов:</p>
    <p>${click_count}</p>
    <form method="POST">
      <button type="submit" name="click" value="clicked">Клик</button>
    </form>
    <c:if test="${inTeam == false}">
    <h4><a href="/create_team">Создать команду</a></h4>
    <h4><a href="/join_team">присоедениться к команде</a></h4>
    </c:if>
    <c:if test="${inTeam == true}">
    <h4><a href="/team">моя команда</a></h4>
    </c:if>
    <h4><a href="/leaderboards">Лидерборды</a></h4>
    <h4><a href="/logout">Выйти из пользователя</a></h4>
  </sec:authorize>
</div>
</body>
</html>