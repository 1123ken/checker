<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.TitleDAO"%>
<%@ page import="model.Title"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TOP</title>
</head>
<body>
	<h1>タイトル検索画面</h1>

	<form id="searchForm" method="get">
		<p>
			何のタイトルについて調べる？<br>
			<input type="text" name="title" placeholder="部分検索可">
			<input type="button" value="検索" onclick="submitForm()">
		</p>
	</form>

	<%
	String keyword = request.getParameter("title");

	// キーワードが指定されている場合にのみ検索実行
	if (keyword != null && !keyword.isEmpty()) {
		try {
			TitleDAO titleDAO = new TitleDAO();
			List<Title> titles = titleDAO.searchTitles(keyword);

			if (!titles.isEmpty()) {
	%>
	<h2>検索結果</h2>
	<ul>
		<%
		for (Title title : titles) {
		%>
		<li><a href="/homeServlet?selectedTitle=<%=title.getTitle()%>"><%=title.getTitle()%></a></li>
		<%
		}
		%>
	</ul>
	<%
	} else {
	%>
	<p>登録がありません</p>
	<%
	}
	} catch (SQLException e) {
	e.printStackTrace();
	}
	}
	%>

	<form onsubmit="return check()" id="registerForm">
		<p>
			タイトルが見つからない場合<br>
			新規登録タイトル登録(正式なタイトル名で登録してください)<br>
			<input type="text" name="addTitle" id="addTitle" placeholder="追加タイトル名"><br>
			キャラクター新規登録<br>
			<input text="text" name="addChara" id="addChara" placeholder="追加キャラクター名"><br>
			<input type="button" value="登録" onclick="register()">
		</p>
	</form>
	
	
	<!-- DBからタイトル情報を検索するJavascript -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
				type : "POST",
				url : "titleCharaRegiServlet",
				data : {
					addTitle : addTitle,
					addChara : addChara
				},
				success : function(response) {
					// 登録成功・失敗メッセージを表示
					alert(response);
				},
				error : function() {
					alert("登録に失敗しました。");
				}
			});
		}
	</script>
</body>
</html>
