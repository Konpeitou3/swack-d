<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Swack - パスワード変更画面</title>
<link rel="shortcut icon" href="images/favicon.ico" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/passwordupdate.css" />
</head>
<body id="body">
	<div class="container">
		<h1>Swack</h1>
		<h2>パスワード変更</h2>
		<div class="card w-100 p-3">
			<!-- エラーメッセージ -->
			<p class="error" id="errorMsg">${errorMsg}</p>

			<!-- ログインフォーム -->
			<!-- was-validated bootstrap バリデーション機能の設定 -->
			<form class="was-validated" action="PasswordUpdateServlet" id="passwordForm" method="post">
			
				<!-- メールアドレス入力 -->
				<input class="form-control" type="email" name="mailAddress" id="mailAddress" placeholder="xxxxxx@xxx.xxx" required></input>
				<!-- 未入力・型違い時の表示内容 -->
				<div class="invalid-feedback"></div>
				<!-- 入力が正常に行われた時の表示内容 -->
				<div class="valid-feedback"></div>
				
				<!-- パスワード入力 -->
				<input class="form-control" type="password" name="password" id="password" placeholder="新しいパスワード" required></input>
				<!-- 未入力・型違い時の表示内容 -->
				<div class="invalid-feedback"></div>
				<!-- 入力が正常に行われた時の表示内容 -->
				<div class="valid-feedback"></div>

				<!-- ログイン -->
				<input class="btn btn-warning" type="submit" value="変更を確定する" onclick="login();" />
				<!-- ログイン情報のセッション保存の選択 -->

			</form>
		</div>
	<a class="btn btn-outline-success" href="LoginServlet" role="button">ログイン画面へ戻る</a>
	<!-- container -->

	<!-- CDN : Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
