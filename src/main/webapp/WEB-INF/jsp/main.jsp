<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Swack - メイン画面</title>
<link rel="shortcut icon" href="images/favicon.ico">

<!-- CDN : Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/main.css">

</head>

<body>
	<div class="container">
		<!-- ヘッダー -->
		<header class="header">
			<!-- ユーザー名表示 -->
			<div>${user.userName}</div>
			<!-- ログアウト処理 -->
			<form action="LogoutServlet" id="logoutForm" method="get">
				<input type="submit" value="ログアウト" onclick="logout();">
			</form>
		</header>

		<section class="main">
			<!-- サイドバー -->
			<div class="left">
				<h2>Swack</h2>
				<hr>
				<!-- プルダウンメニュー -->
				<details open>
					<!-- メニュー名 -->
					<summary>
						ルーム

						<!-- ルーム作成 -->
						<a href="CreateRoomServlet?roomId=${room.roomId}">
							<button>＋</button>
						</a>

					</summary>

					<!-- ルームリスト -->
					<c:forEach var="room" items="${roomList}">
						<a class="list-name" href="MainServlet?roomId=${room.roomId}">#
							${room.roomName}</a>
						<br>
					</c:forEach>

					<!-- ルーム参加 -->
					<a class="list-name" href="JoinRoomServlet">+ ルーム参加</a>

				</details>

				<!-- プルダウンメニュー -->
				<details open>
					<!-- メニュー名 -->
					<summary> ダイレクト </summary>

					<!-- ダイレクトルームリスト -->
					<c:forEach var="direct" items="${directList}">
						<a class="list-name" href="MainServlet?roomId=${direct.roomId}">#
							${direct.roomName}</a>
						<br>
					</c:forEach>

				</details>
			</div>
			<!--left -->

			<!-- コンテンツ -->
			<div class="contents">

				<!-- ダイレクトメッセージルーム以外のルームの時表示 -->
				<c:if test="${!room.directed}">

					<!-- ルーム招待 -->
					<div class="join-member-button">
						<a href="JoinMemberServlet?roomId=${room.roomId}">
							<button>＋</button>
						</a>
					</div>

				</c:if>

				<h2>
				<div class="buttonimage">
					<!-- ルーム名 -->
					${room.roomName}(${room.memberCount})
					<!-- 再読み込み -->
					<input type="hidden" id="Admin" value="${Admin}">
					<!-- 強制退会ボタン -->
					<a href="DeleteUserServlet"><input type="image" src="images/Delete-Account.svg" class="delete" id="Adminbutton" disabled/></a>
					<!-- アカウントロック解除ボタン -->
					<c:if test="${Admin }">
					<a href="AccountUnrockServlet"><input type="image" src="images/Delete-Account.svg" class="delete" id="AccountUnlockbutton"/></a>
					</c:if>
					</div>
				</h2>
				<hr>

				<!-- チャット表示 -->
				<div id="logArea" class="contents-main">

					<!-- チャット履歴表示 -->
					<c:forEach var="chatLog" items="${chatLogList}" varStatus="status">

						<!-- 最新のチャット履歴 -->
						<c:if test="${status.last}">
							<!-- 引き渡し用の最新のチャットのIDを挿入（hidden） -->
							<input type="hidden" id="lastChatLogId"
								value="${chatLog.chatLogId}">
						</c:if>

						<!-- チャットの履歴の表示方法 -->
						<div class="log-area" id="${chatLog.chatLogId}">
							<!-- チャットに入力した人のアイコン -->
							<div class="log-icon">
								<img src="images/${chatLog.userId}.png"
									onerror="this.src='images/profile.png;'">
							</div>

							<!-- チャット内容 -->
							<div class="log-box">
								<!-- 投稿主情報 -->
								<p class="log-name">
									<!-- 投稿主のユーザー名 -->
									${chatLog.userName}
									<!-- 投稿時刻 -->
									<span class="log-time">[${chatLog.createdAt}]</span>
									<a class="btn btn-outline-danger" href="DeleteMessageServlet?chatLogId=${chatLog.chatLogId}" role="button">×</a>
								</p>
								<!-- 投稿内容 -->
								<p>${chatLog.message}</p>
							</div>
						</div>
					</c:forEach>
				</div>

				<!-- コンテンツフッター -->
				<div class="contents-footer">

					<!-- 投稿フォーム -->
					<form action="MainServlet" method="post" id="messageForm">
						<!-- 引き渡し用の投稿したいルームIDを挿入（hidden） -->
						<input type="hidden" name="roomId" value="${room.roomId}">
						<!-- 投稿内容 -->
						<div class="form-wrap">
							<!-- 投稿メッセージ -->
							<!-- autocomplete="off" 未入力時 送信ボタンが押せないようにしている -->
							<input type="text" name="message" id=message autocomplete="off">
							<!-- 投稿送信 -->
							<input type="submit" value="送信" id="send">
						</div>
					</form>

				</div>
			</div>
			<!--contents -->
		</section>
		<!--main -->
	</div>
	<!-- container -->

	<!-- CDN : Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<!-- script読み込み -->
	<script src="js/main.js"></script>

</body>
</html>
