<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
  <title>user</title>
  <p>click count:</p>
  <p>${user_click_count}</p>
  <p>team click count:</p>
  <p>${team_click_count}</p>
    <form method="POST">
         <button type="submit" name="command" value="leave">leave team</button>
    </form>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<div>
  <p>hi from user</p>
</div>
</body>
</html>