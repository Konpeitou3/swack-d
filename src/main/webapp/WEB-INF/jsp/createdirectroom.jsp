<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Swack - ダイレクトチャット作成画面</title>

<link rel="shortcut icon" href="images/favicon.ico" />

<!-- CDN : Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />

<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/createroom.css" />
</head>

<body>
	<div class="container form-container">
		<div class="row">
			<div class="col-md-12 room-form">
				<h3>ダイレクトチャットを作成する</h3>
				<p class="input_note_special medium_bottom_margin">
					ダイレクトチャットとはメンバーが個人的なコミュニケーションを取る場所です。特定の人に対してメッセージを送りたいときに作成すると良いでしょう
				</p>

				<form action="CreatedirectRoomServlet" id="createForm" method="post">

					<!-- 招待先の選択フォーム -->
					<div class="form-group mt-5">
						<label class="control-label">チャット相手を選択</label>
						<select id="names" class="form-select" name="selectUser" multiple>
							<c:forEach var="user" items="${usersList}">
								<option value="${user.userId}">
									<c:out value="${user.userName}" />
								</option>
							</c:forEach>
						</select>
						<span class="users-note">チャットを開始したいユーザーを選んでください</span>
					</div>

					<!-- キャンセルボタン ルーム作成実行ボタン -->
					<div class="room-form-btn">
						<a href="MainServlet?roomId=${roomId}">
							<input type="button" class="btn btn-default" value="キャンセル" />
						</a>
						<button id="send" class="btn btn-default">ダイレクトチャットを作成する</button>
					</div>
					
				</form>
			</div>
		</div>
	</div>
	<!-- container -->

	<!-- CDN : Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<script src="js/createroom.js"></script>
</body>
</html>
