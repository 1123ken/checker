<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.TitleDAO"%>
<%@ page import="model.Title"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ようこそ！</title>
</head>
<body>
	
	<h1>対戦ゲーム対策掲示板</h1>
	
	<div>
	<h3>タイトル検索</h3>
	</div>

	<form id="searchForm" method="get">
		<p>
			<input type="text" name="title" placeholder="部分検索可" required>
			<input type="button" value="検索" onclick="submitForm()">
		</p>
	</form>
	
<!-- 検索結果を表示して表示したページに画面遷移する -->
<%
String keyword = request.getParameter("title");

// キーワードが指定されている場合にのみ検索実行
if (keyword != null && !keyword.isEmpty()) {
	try {
		TitleDAO titleDAO = new TitleDAO();
		List<Title> titles = titleDAO.searchTitles(keyword);

		if (!titles.isEmpty()) { %>
	<h2>検索結果</h2>
	<ul>
		<% for (Title title : titles) { %>
		<li><a href="<%=request.getContextPath()%>/homeServlet?selectedTitle=<%=title.getTitle()%>"><%=title.getTitle()%></a></li>
		<% } %>
	</ul>
	<% } else { %>
		<p>登録がありません</p>
	<% } } catch (SQLException e) {
		e.printStackTrace();
		}} %>
	
	<!-- 追加済みタイトル一覧 -->
	<h2>登録済みタイトル</h2>
	<ul>
    <%
    List<String> titleList = (List<String>) request.getAttribute("titleList");
    if (titleList != null && !titleList.isEmpty()) {
        for (String title : titleList) {
            // カンマで分割し、タイトル名の部分だけを取得
            String[] parts = title.split(",");
            String titleName = parts.length > 1 ? parts[1].trim() : title;
    %>
        <li><a href="<%=request.getContextPath()%>/homeServlet?selectedTitle=<%=titleName%>"><%=titleName%></a></li>
    <% } } else { %>
        <p>タイトルが登録されていません</p>
    <% } %>
	</ul>
	
	<!-- タイトル、キャラクター登録 -->
	<form onsubmit="return check()" id="registerForm">
		<p>
			タイトル新規追加(上記に登録ある場合でも入力可))<br>
			<input type="text" name="addTitle" id="addTitle" placeholder="追加タイトル名" required><br>
			キャラクター新規登録<br>
			<input text="text" name="addChara" id="addChara" placeholder="追加キャラクター名" required><br>
		</p>
		<input type="button" value="登録" onclick="register()">
	</form>
	
	<!--動的に変化させるためにajaxを適用 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<!-- タイトル検索をするJavascript -->
	<script type="text/javascript">
		function submitForm() {
			document.getElementById("searchForm").submit();
		}
	</script>
	<!-- DBにタイトルとキャラクターを登録するjavascript -->
<script type="text/javascript">
    function register() {
        var addTitle = document.getElementById("addTitle").value;
        var addChara = document.getElementById("addChara").value;

        $.ajax({
            type: "POST",
            url: "titleCharaRegiServlet",
            data: {
                addTitle: addTitle,
                addChara: addChara
            },
            success: function (response) {
                if (response.trim() == "success") {
                    alert("キャラクターを登録しました。");
                    location.reload();
                } else if (response.trim() == "duplicateCharacter") {
                    alert("キャラクターは既に存在します。");
                } else {
                    alert("登録に失敗しました。");
                }
            },
            error: function () {
                alert("通信エラーが発生しました。");
            }
        });
    }
</script>
</body>
</html>
