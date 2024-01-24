<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Title"%>
<%@ page import="dao.TitleDAO"%>
<%@ page import="beans.Character"%>

<%
String selectedTitle = request.getParameter("selectedTitle");

if (selectedTitle != null && !selectedTitle.isEmpty()) {
	TitleDAO titleDAO = new TitleDAO();
	int titleId = titleDAO.getTitleId(selectedTitle);

	List<GameCharacter> characters = titleDAO.getCharactersWithIds(titleId);
	request.setAttribute("characters", characters);
}
%>

<html>
<head>
<meta charset="UTF-8">
<title>登録画面</title>
</head>
<body>
	<h1>${selectedTitle}のページ</h1>
	<br>
	<form action="SaveDataServlet" method="post">
		<label for="myCharacter">自操作キャラ:</label>
		<select name="myCharacter">
			<c:forEach var="character" items="${characters}">
				<option value="${character.id}">${character.name}</option>
			</c:forEach>
		</select>
		<label for="yourCharacter">対策キャラ:</label>
		<select name="yourCharacter">
			<c:forEach var="character" items="${characters}">
				<option value="${character.id}">${character.name}</option>
			</c:forEach>

			<!-- javascriptの＋－ボタンで入力ボックスを増やす -->
			<div id="input_pluralBox">
				<div id="input_plural">
					<form action="/registerDataServlet" method="post">
						勝<input type="radio" name="worl" value="1"> 負<input
							type="radio" name="worl" value="0"><br> キツイ所<br>
						<input type="text" name="point" class="form-control"
							placeholder="この技がきつい等"><br> 対策<br>
						<textarea name="cpoint" class="form-control" placeholder="全体的な対策"
							cols="30" rows="10"></textarea>
						<br> <input type="button" value="＋" class="add pluralBtn">
						<input type="button" value="－" class="del pluralBtn"> <input
							type="submit" value="登録">
					</form>
				</div>
			</div>
			<!-- ここまで -->
	</form>

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
