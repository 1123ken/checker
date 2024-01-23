<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Title"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Character" %>
<%@ page import="dao.TitleDAO" %>
<%
    // 選択されたタイトルを取得
    String selectedTitle = (String) request.getAttribute("selectedTitle");

    // タイトルDAOをインスタンス化
    TitleDAO titleDAO = new TitleDAO();

    // 登録されているキャラクター情報を取得
    List<Character> characters = titleDAO.getCharacters(selectedTitle);
    request.setAttribute("characters", characters);
%>
<html>
<head>
<meta charset="UTF-8">
<title>登録画面</title>
</head>
<body>
	<h1>${selectedTitle}のページ</h1><br>
	<p>

		<input type="text" name="title"><br> 自操作キャラ<br> <input
			type="text" name="myChara"><br> 対策キャラ<br> <input
			type="text" name="yourChara"><br>
	</p>

	<!-- javascriptの＋－ボタンで入力ボックスを増やす -->
	<div id="input_pluralBox">
		<div id="input_plural">
			<form action="/registerDataServlet" method="post">
				勝<input type="radio" name="worl"> 負<input type="radio" name="worl"><br>
				キツイ所<br> <input type="text" name="point" class="form-control" placeholder="この技がきつい等"><br>
				対策<br>
				<textarea name="cpoint" class="form-control" placeholder="全体的な対策"></textarea><br>
				 <input type="button" value="＋" class="add pluralBtn">
				<input type="button" value="－" class="del pluralBtn">
				<input type="submit" value="登録">
			</form>
		</div>
	</div>
	<!-- ここまで -->

	<form action="/topServlet">
		<input type="submit" value="TOPへ">
	</form>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).on("click", ".add", function() {
			$(this).parent().clone(true).insertAfter($(this).parent());
		});
		$(document).on("click", ".del", function() {
			var target = $(this).parent();
			if (target.parent().children().length > 1) {
				target.remove();
			}
		});
	</script>
</body>
</html>