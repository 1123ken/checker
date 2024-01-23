<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.TitleDAO" %>
<%@ page import="model.Title" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TOP</title>
    <script type="text/javascript">
        function submitForm() {
            document.getElementById("searchForm").submit();
        }
    </script>
</head>
<body>
    <h1>タイトル検索画面</h1>
    
    <form id="searchForm" method="get">
        <p>
            何のタイトルについて調べる？<br>
            <input type="text" name="title" placeholder="ゲームのタイトル名">
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
                    <h2>Search Results:</h2>
                    <ul>
                        <% for (Title title : titles) { %>
                            <li><a href="/homeServlet?selectedTitle=<%= title.getTitle() %>"><%= title.getTitle() %></a></li>
                        <% } %>
                    </ul>
    <%
                } else {
    %>
                    <p>No results found.</p>
    <%
                }
            } catch (SQLException e) {
                e.printStackTrace(); // エラーハンドリングは必要に応じて追加
            }
        }
    %>

    <form onsubmit="return check()">
        <p>
            タイトルが見つからない場合<br> 新規登録タイトル登録<br>
            <input type="text" name="addTitle" placeholder="追加タイトル名">
            <input text="text" name="addChara" placeholder="追加キャラクター名">
            <input type="submit" value="登録">
        </p>
    </form>

    <script type="text/javascript">
        function check() {
            if (window.confirm('タイトルを登録してもよろしいですか？')) {
                return true;
            } else {
                window.alert('キャンセルされました');
                return false;
            }
        }
    </script>

    <%-- 入力されたタイトルを表示 --%>
    <%
        if (keyword != null && !keyword.isEmpty()) {
    %>
        <h2>検索したタイトル:</h2>
        <p><%= keyword %></p>
    <%
        }
    %>

</body>
</html>
