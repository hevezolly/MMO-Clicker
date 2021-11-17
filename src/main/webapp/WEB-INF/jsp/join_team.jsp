<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" errorPage="loginError" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Присоединиться к команде</title>
</head>

<body>
<c:if test="${not empty param.error}">Такой команды не существует</c:if>
<div>
  <form method="POST" action="/join_team">
    <h2>Введите имя команды</h2>
    <div>
      <input name="team_name" type="text" placeholder="Имя команды"
             autofocus="true"/>
      <button type="submit">Присоединиться</button>
      <h4><a href="/">Главная</a></h4>
    </div>
  </form>
</div>

</body>
</html>