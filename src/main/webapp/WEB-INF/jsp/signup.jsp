<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Swack - 新規登録画面</title>
<link rel="shortcut icon" href="images/fabicon.ico">

<!-- CDN : Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/signup.css">
</head>
<body>

	<div class="container">
		<h1>Swack</h1>
		<h2>新規登録</h2>
		<div class="card  w-100 p-3">
			<h4 class="subtitle">Swackワークスペースに参加する</h4>
			<p>氏名、メールアドレス、パスワードを入力してください。</p>
			<p class="error">${errorMsg}</p>
			<form class="was-validated" action="SignUpServlet" method="post">
				<input class="form-control" type="text" name="userName" placeholder="情報太郎" required></input>
    			<div class="invalid-feedback">
				</div>
    			<div class="valid-feedback">
    			</div>
    			<input class="form-control" type="email" name="mailAddress" placeholder="xxx@xxx.xx" required></input>
    			<div class="invalid-feedback">
				</div>
    			<div class="valid-feedback">
    			</div>
    			<input class="form-control" type="password" name="password" placeholder="パスワード" required></input>
    			<div class="invalid-feedback">
				</div>
    			<div class="valid-feedback">
    			</div>
				<input class="btn btn-denger" type="submit" value="参加する">
			</form>
		</div>
		<a class="btn btn-outline-primary" href="LoginServlet" role="button">ログイン画面へ</a>
	</div>
	<!-- container -->

	<!-- CDN : Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>