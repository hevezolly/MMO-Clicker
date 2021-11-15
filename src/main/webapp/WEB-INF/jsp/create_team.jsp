<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Создание команды</title>
</head>

<body>
<div>
  <form:form method="POST" modelAttribute="teamForm">
    <h2>Создание команды</h2>
    <div>
      <form:input type="text" path="team_name" placeholder="TeamName"
                  autofocus="true"></form:input>
      <form:errors path="team_name"></form:errors>
        ${teamNameError}
      <form:errors path="team_name"></form:errors>
        ${Error}
    </div>
    <button type="submit">Создать</button>
  </form:form>
  <a href="/">Главная</a>
</div>
</body>
</html>