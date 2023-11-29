<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Swack - ルーム作成画面</title>

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
				<h3>ルームを作成する</h3>
				<p class="input_note_special medium_bottom_margin">
					ルームとはメンバーがコミュニケーションを取る場所です。特定のトピックに基づいてルームを作ると良いでしょう (例: #営業)。
				</p>

				<form action="CreateRoomServlet" id="createForm" method="post">
					<div class="form-check form-switch mt-3">
						<!-- プライベートorパブリックの設定(チェックボックスを変えたときに -->
						<!-- 出てくる文章などはcreateroom.jsを参照) -->
						<input class="form-check-input" id="chk" name="setting" type="checkbox" autocomplete="off" />
							<label class="form-check-label" class="btn btn-secondary active">
								<span class="check_label">パブリック</span>
							</label><br />
							<span class="check_text">このルームは、ワークスペースのメンバーであれば誰でも閲覧・参加することができます。</span>
					</div>

					<!-- ルーム名の入力フォーム -->
					<div class="form-group mt-5">
						<label class="control-label">名前</label>
						<input id="name" class="form-control" type="text" name="roomName" placeholder="# 例:営業" autofocus />
						<span class="name-note">ルームの名前を入力してください。</span>
					</div>

					<!-- 招待先の選択フォーム -->
					<div class="form-group mt-5">
						<label class="control-label">招待の送信先:(任意)</label>
						<select id="names" class="form-select" name="selectUser" multiple>
							<c:forEach var="user" items="${usersList}">
								<option value="${user.userId}">
									<c:out value="${user.userName}" />
								</option>
							</c:forEach>
						</select>
						<span class="users-note">このルームに追加したい人を選んでください。</span>
					</div>

					<!-- キャンセルボタン ルーム作成実行ボタン -->
					<div class="room-form-btn">
						<a href="MainServlet?roomId=${roomId}">
							<input type="button" class="btn btn-default" value="キャンセル" />
						</a>
						<button id="send" class="btn btn-default">ルームを作成する</button>
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
