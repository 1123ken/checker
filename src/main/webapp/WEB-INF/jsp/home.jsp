<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ import pagebeans.beans() %>
<%
   Beans bs = (Beans) session.getAttribute("title");
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム画面</title>
</head>
<body>
	タイトル名
	<br>
	<% bs.getTitle() %>
	

</body>
</html>