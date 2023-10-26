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
			<p class="error" id="errorMsg">${errorMsg}</p>
			<form class="was-validated" action="LoginServlet" id="loginForm" method="post">
			<input class="form-control" type="email" name="mailAddress" id="mailAddress" placeholder="xxxxxx@xxx.xxx" required></input>
    		<div class="invalid-feedback">
    		</div>
    		<div class="valid-feedback">
    		</div>
    		<input class="form-control" type="password" name="password" id="password"  placeholder="パスワード" required></input>
    		<div class="invalid-feedback">
    		</div>
    		<div class="valid-feedback">
    		</div>
				<input class="btn btn-primary" type="submit" value="ログイン" onclick="login();" />
				<label>
					<input type="checkbox" id="save" /><span>ログイン状態を保持する</span>
				</label>
			</form>
		</div>

		<a class="btn btn-outline-success" href="SignUpServlet" role="button">新規登録画面へ</a>
	</div>
	<!-- container -->
	<script src="js/login.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
