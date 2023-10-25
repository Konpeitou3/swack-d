<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Swack - 新規登録画面</title>
<link rel="shortcut icon" href="images/fabicon.ico">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/signup.css">
</head>
<body>

<div class="container">
	<h1>Swack</h1>
	<h2>新規登録</h2>
	<div class="card">
		<h3 class="subtitle">Swackワークスペースに参加する</h3>
		<h4>氏名、メールアドレス、パスワードを入力してください。</h4>
		<form action="SignUpServlet" method="post">
		<input type="text" name="userName" placeholder="情報太郎"><br>
		<input type="email" name="mailAddress" placeholder="xxx@xxx.xx"><br>
		<input type="password" name="password" placeholder="パスワード"><br>
		<input type="submit" value="参加する">
		</form>
	</div>
	
	<a href="LoginServlet">＞＞ログイン画面へ</a>
</div>

</body>
</html>