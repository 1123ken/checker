<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Title"%>
<%@ page import="dao.TitleDAO"%>
<%@ page import="model.GameCharacter"%>
<%@ page import="dao.CharacterDAO"%>
<%@ page import="beans.Beans"%>

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
    <h1><%=gc.getSelectedTitle()%>のページ</h1>
    <br>
    <form action="/checker/SaveDataServlet" method="post">
        <!-- hidenで表示されないがtitleIdの情報を送る処理の一文 -->
        <input type="hidden" name="titleId" value="<%=gc.getTitleId()%>">
        <!-- 自操作キャラクター選択用のselectタグ -->
        <label for="myCharacter">使用キャラ：</label><br> <select
            id="myCharacter" name="myCharacter">
            <option value="" disabled selected>キャラクターを選択してください</option>
            <%
            for (String chara : characters) {
            %>
            <option value="<%=chara%>"><%=chara%></option>
            <%
            }
            %>
        </select> <br>
        <!-- 対策キャラクター選択用のselectタグ -->
        <label for="yourCharacter">対策キャラクター選択：</label><br> <select
            id="yourCharacter" name="yourCharacter">
            <option value="" disabled selected>キャラクターを選択してください</option>
            <%
            for (String chara : characters) {
            %>
            <option value="<%=chara%>"><%=chara%></option>
            <%
            }
            %>
        </select> <br>
        <!-- javascriptの＋－ボタンで入力ボックスを増やす -->
        <div id="input_pluralBox">
            <div id="input_plural">
                勝<input type="radio" name="worl" value="1" checked> 負<input
                    type="radio" name="worl" value="0"><br> キツイ所<br>
                <input type="text" name="point" class="form-control"
                    placeholder="この技がきつい等" required><br> 対策<br>
                <textarea name="cpoint" class="form-control" placeholder="全体的な対策"
                    cols="30" rows="10" style="resize: none;" required></textarea>
                <br> <input type="submit" value="登録">
            </div>
        </div>
    </form>
    <!-- ここまで -->

    <h2>過去の投稿</h2>
    <table border="1">
        <thead>
            <tr>
                <!-- "対戦キャラ"の<th>を削除 -->
                <th>勝敗</th>
                <th>きつい技</th>
                <th>対策</th>
            </tr>
        </thead>
        <tbody id="dataBody">
            <%
            List<Beans> beansList = (List<Beans>) request.getAttribute("beansList");
            if (beansList != null && !beansList.isEmpty()) {
                for (Beans beans : beansList) {
            %>
            <tr>
                <!-- <td><%=beans.getYourChara()%></td> を削除 -->
                <td><%=beans.getWorl() ? "勝ち" : "負け"%></td>
                <td><%=beans.getPoint()%></td>
                <td><%=beans.getCpoint()%></td>
            </tr>
            <%
            }
            } else {
            %>
            <tr>
                <td colspan="3">データがありません</td>
            </tr>
            <%
            }
            %>
        </tbody>
    </table>
	
	<!-- 余裕があれば依然追加した対策部分に削除ボタンを作成する -->
	
    <!-- 前のページに戻る -->
    <form action="/checker/topServlet">
        <input type="submit" value="TOPへ">
    </form>

    <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#myCharacter, #yourCharacter").change(function() {
                var myCharacter = $("#myCharacter").val();
                var yourCharacter = $("#yourCharacter").val();

                // myCharacterかyourCharacterの値が存在しない場合はデータの取得をスキップ
                if (!myCharacter || !yourCharacter) {
                    return;
                }

                $.ajax({
                    type: "GET",
                    url: "<%= request.getContextPath() %>/checker/GetDataServlet",
                    data: {
                        myCharacter: myCharacter,
                        yourCharacter: yourCharacter
                    },
                    success: function(response) {
                        displayData(response);
                    },
                    error: function() {
                        alert("データの取得に失敗しました。");
                    }
                });
            });

            function displayData(data) {
                var tableBody = $("#dataBody");
                tableBody.empty();

                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var row = "<tr>" +
                            // "<td>" + data[i].yourChara + "</td>" を削除
                            "<td>" + (data[i].worl ? "勝利" : "敗北") + "</td>" +
                            "<td>" + data[i].point + "</td>" +
                            "<td>" + data[i].cpoint + "</td>" +
                            "</tr>";
                        tableBody.append(row);
                    }
                } else {
                    var noDataRow = "<tr><td colspan='3'>データがありません</td></tr>";
                    tableBody.append(noDataRow);
                }
            }
        });
    </script>

</body>
</html>
