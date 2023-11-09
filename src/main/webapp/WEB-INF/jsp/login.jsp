-<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Swack - ログイン画面</title>
<link rel="shortcut icon" href="images/favicon.ico" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/login.css" />
</head>
<body id="body">
	<div class="container">
		<h1>Swack</h1>
		<h2>ログイン</h2>
		<div class="card w-100 p-3">
			<!-- エラーメッセージ -->
			<p class="error" id="errorMsg">${errorMsg}</p>
			<!-- 新規登録から正常に遷移された時の新規登録成功メッセージ -->
			<p class="success" id="successMsg">${successMsg}</p>

			<!-- ログインフォーム -->
			<!-- was-validated bootstrap バリデーション機能の設定 -->
			<form class="was-validated" action="LoginServlet" id="loginForm" method="post">
			
				<!-- メールアドレス入力 -->
				<input class="form-control" type="email" name="mailAddress" id="mailAddress" placeholder="xxxxxx@xxx.xxx" required></input>
				<!-- 未入力・型違い時の表示内容 -->
				<div class="invalid-feedback"></div>
				<!-- 入力が正常に行われた時の表示内容 -->
				<div class="valid-feedback"></div>
				
				<!-- パスワード入力 -->
				<input class="form-control" type="password" name="password" id="password" placeholder="パスワード" required></input>
				<!-- 未入力・型違い時の表示内容 -->
				<div class="invalid-feedback"></div>
				<!-- 入力が正常に行われた時の表示内容 -->
				<div class="valid-feedback"></div>

				<!-- ログイン -->
				<input class="btn btn-primary" type="submit" value="ログイン" onclick="login();" />
				<!-- ログイン情報のセッション保存の選択 -->
				<label> <input type="checkbox" id="save" /><span>ログイン状態を保持する</span>
				</label>

			</form>
		</div>
		<div class="linksparent">
		
		<!-- 新規登録画面遷移 -->
		<a class="btn btn-outline-success" href="SignUpServlet" role="button">新規登録画面へ</a>
		<!-- パスワード再設定画面遷移 -->
		<a class="link btn btn-outline-primary" href="PasswordUpdateSevlet">パスワードを忘れた方はこちら</a>
		</div>
	</div>
	<!-- container -->

	<!-- script読み込み -->
	<script src="js/login.js"></script>

	<!-- CDN : Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
