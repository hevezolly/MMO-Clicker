<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.clicker.Clicker.entities.forms.ItemForm"%>

<!DOCTYPE HTML>
<html>
<head>
  <title>Лидерборды</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h3>Предметы для игрока</h3>
<table border="1" cellspacing="0" cellpadding="2">
    <c:forEach items="${teamItems}" var="item">
        <tr>
            <form method="POST">
                <button type="submit" name="command" value="buy_user_${item.index}">
                    <h4>${item.name}</h4>
                    <p>${item.description}</p>
                </button>
            </form>
        </tr>
    </c:forEach>
</table>
<c:if test="${not empty teamItems}">
    <h3>Предметы для команды</h3>
    <table border="1" cellspacing="0" cellpadding="2">
        <c:forEach items="${teamItems}" var="item">
            <tr>
                <form method="POST">
                    <button type="submit" name="command" value="buy_team_${item.index}">
                        <h4>${item.name}</h4>
                        <p>${item.description}</p>
                    </button>
                </form>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="/">Главная</a>
</body>
</html>