<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
  <title>Команда</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<p>Количество кликов команды:</p>
<p>${team_click_count}</p>
<form method="POST">
     <button type="submit" name="command" value="delete">Удалить команду</button>
</form>
<a href="/">Главная</a>
</body>
</html>