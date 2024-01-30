/**
 * top.jspで使用する
 */


//タイトル検索をするJavascript
function submitForm() {
	document.getElementById("searchForm").submit();
}

// タイトルとキャラクターを登録する関数
function register() {
    // 入力フォームからタイトルとキャラクターの値を取得
    var addTitle = document.getElementById("addTitle").value;
    var addChara = document.getElementById("addChara").value;

    // Ajaxリクエストを発行
    $.ajax({
        type: "POST", // POSTメソッドを使用
        url: "TitleCharaRegiServlet", // サーブレットのURL
        data: {
            addTitle: addTitle, // タイトルをデータに含める
            addChara: addChara // キャラクターをデータに含める
        },
        success: function(response) {
            // サーブレットからの正常なレスポンスの処理
            // サーブレットが "キャラクターを新規登録しました" と返した場合
            if (response.trim() == "キャラクターを新規登録しました") {
                alert("キャラクターを登録しました。");
                location.reload(); // ページを再読み込み
            } 
            // サーブレットが "すでに登録されています" と返した場合
            else if (response.trim() == "すでに登録されています") {
                alert("すでに登録されています。");
            } 
            //サーブレットが"タイトルとキャラクターを新規登録しました"と返した場合
            else if (response.trim() == "タイトルとキャラクターを新規登録しました") {
                alert("タイトルとキャラクターを新規登録しました。");
                location.reload(); // ページを再読み込み
            } 
            else {
                alert("登録に失敗しました。");
            }
        },
        error: function() {
            // Ajaxリクエストが失敗した場合の処理
            alert("通信エラーが発生しました。");
        }
    });
}


