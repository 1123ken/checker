<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Title"%>
<%@ page import="dao.TitleDAO"%>
<%@ page import="beans.GameCharacter"%>
<%@ page import="dao.CharacterDAO"%>
<%
GameCharacter gc = (GameCharacter) session.getAttribute("キャラクター情報");
String selectedTitle = (String) session.getAttribute("選択したタイトル名");
List<Title> titles = (List<Title>) session.getAttribute("タイトル名");
List<String> characters = (List<String>) session.getAttribute("登録キャラクターリスト");
%>

<html>
<head>
<meta charset="UTF-8">
<title>登録画面</title>
</head>
<body>
	<h1><%=gc.getSelectedTitle()%>のページ
	</h1>
	<br>
	<!-- 自操作キャラクター選択用のselectタグ -->
	<form action="/checker/SaveDataServlet" method="post">
		<label for="myCharacter">使用キャラ：</label><br>
		<select id="myCharacter" name="myCharacter">
    		<% for (String chara : characters) { %>
       		 <option value="<%=chara%>"><%=chara%></option>
    		<% } %>
		</select> <br>
		<!-- 対策キャラクター選択用のselectタグ -->
		<label for="yourCharacter">対策キャラクター選択：</label><br>
		<select id="yourCharacter" name="yourCharacter">
    		<% for (String chara : characters) { %>
       		 <option value="<%=chara%>"><%=chara%></option>
    		<% } %>
		</select> <br>
		<!-- javascriptの＋－ボタンで入力ボックスを増やす -->
		<div id="input_pluralBox">
			<div id="input_plural">
				勝<input type="radio" name="worl" value="1">
				負<input type="radio" name="worl" value="0"><br>
				キツイ所<br>
				<input type="text" name="point" class="form-control" placeholder="この技がきつい等"><br>
				対策<br>
				<textarea name="cpoint" class="form-control" placeholder="全体的な対策" cols="30" rows="10"></textarea><br>
				<input type="button" value="＋" class="add pluralBtn">
				<input type="button" value="－" class="del pluralBtn">
				<input type="submit" value="登録">
			</div>
		</div>
	</form>
	<!-- ここまで -->

	<!-- 前のページに戻る -->
	<form action="/checker/topServlet">
		<input type="submit" value="TOPへ">
	</form>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="/js/home.js"></script>
</body>
</html>
