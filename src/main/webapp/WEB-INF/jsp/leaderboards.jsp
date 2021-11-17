<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.clicker.Clicker.entities.LeaderboardRow"%>

<!DOCTYPE HTML>
<html>
<head>
  <title>Лидерборды</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h3>Команды</h3>
<table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <td>Команда</td>
        <td>Очки</td>
    </tr>

    <c:forEach items="${teamsLeaderboard}" var="team">
        <tr>
            <td>${team.name}</td>
            <td>${team.score}</td>
        </tr>
    </c:forEach>
</table>
<c:if test="${not empty usersLeaderboard}">
    <h3>Лидерборд для ${teamName}</h3>
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td>Игрок</td>
            <td>Очки</td>
        </tr>

        <c:forEach items="${usersLeaderboard}" var="user">
            <tr>
                <td>${user.name}</td>
                <td>${user.score}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="/">Главная</a>
</body>
</html>