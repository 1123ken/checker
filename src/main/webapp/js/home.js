/**
 * home.jspで使用するがうまくいかないのでデータだけ残しておく
 * home.jspは動く形で保存済み
*/

// ドキュメントが完全に読み込まれた後に実行される処理
$(document).ready(function() {

    // #myCharacter および #yourCharacter の値が変更されたときに実行される処理
    $("#myCharacter, #yourCharacter").change(function() {

        // #myCharacter と #yourCharacter の値を取得
        var myCharacter = $("#myCharacter").val();
        var yourCharacter = $("#yourCharacter").val();

        // myCharacterかyourCharacterの値が存在しない場合はデータの取得をスキップ
        if (!myCharacter || !yourCharacter) {
            return;
        }

        // Ajaxリクエストを発行
        $.ajax({
            type: "GET", // GETメソッドを使用
            url: contextPath + "/checker/GetDataServlet", // コンテキストパスを組み込んだURL
            data: {
                myCharacter: myCharacter,
                yourCharacter: yourCharacter
            },
            success: function(response) {
                // サーバーからの正常なレスポンスの処理
                displayData(response);
            },
            error: function() {
                // Ajaxリクエストが失敗した場合の処理
                alert("データの取得に失敗しました。");
            }
        });
    });

    // 取得したデータを表示する関数
    function displayData(data) {
        var tableBody = $("#dataBody");
        tableBody.empty();

        // データが存在するかチェック
        if (data != null && data.length > 0) {
            // データが存在する場合、テーブルにデータを追加
            for (var i = 0; i < data.length; i++) {
                var row = "<tr>" +
                    "<td>" + data[i].point + "</td>" +
                    "<td>" + data[i].cpoint + "</td>" +
                    "</tr>";
                tableBody.append(row);
            }
        } else {
            // データが存在しない場合、メッセージを表示
            var noDataRow = "<tr><td colspan='3'>データがありません</td></tr>";
            tableBody.append(noDataRow);
        }
    }
});

