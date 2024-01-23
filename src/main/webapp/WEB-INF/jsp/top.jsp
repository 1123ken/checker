<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>タイトル検索画面</h1>
	<form 　action="/checker/charaSerblet">
		<p>
			何のタイトルについて調べる？<br>
			<input type="text" name="title" placeholder="ゲームのタイトル名">
			<input type="submit" value="検索">
		</p>
	</form>
	<form onsubmit="return check()">
		<p>
			タイトルが見つからない場合<br> 新規登録タイトル登録<br>
			<input type="text" name="addTitle" placeholder="追加タイトル名">
			<input text="text"  name="addChara" placeholder="追加キャラクター名">
			<input type="submit" value="登録">
		</p>
	</form>

	<script type="text/javascript">
		function check() {
			if (window.confirm('タイトルを登録してもよろしいですか？')) { // 確認ダイアログを表示
				return true; // 「OK」時は送信を実行
			} else { // 「キャンセル」時の処理
				window.alert('キャンセルされました'); // 警告ダイアログを表示
				return false; // 送信を中止
			}
		}
	</script>

</body>
</html>