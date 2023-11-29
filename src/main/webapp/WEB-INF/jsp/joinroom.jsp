<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Swack - ルーム参加画面</title>

<link rel="shortcut icon" href="images/favicon.ico" />

<!-- CDN : Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />

<!-- フォントを使用するため外部のサイトの読み込み -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@600&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/joinroom.css" />
</head>

<body>
	<div class="container form-container">
		<div class="row">
			<div class="col-md-12 room-form">
				<h3>ルームに参加する</h3>
				<!-- ルーム参加フォーム -->
				<form action="JoinRoomServlet" method="post">

					<!-- 遷移先の設定(hidden) -->
					<input type="hidden" name="roomId" value="${roomId}" />

					<div class="form-group">
						<label class="control-label">ルームの参加先:(任意)</label>
						<c:if test="${empty roomList}">
							<span class="not-found-room">※参加できるルームがありません</span>
						</c:if>
						<select id="rooms" name="selectRoom" class="form-select" multiple>
							<!-- まだ参加していないルームの表示 -->
							<c:forEach var="room" items="${roomList}">
								<option value="${room.roomId}">
									<c:out value="${room.roomName}" />
								</option>
							</c:forEach>
						</select>
						<span class="rooms-note">参加したいルームを選んでください。</span>
					</div>

					<div class="room-form-btn">
						<!-- 操作キャンセル -->
						<a href="MainServlet?roomId=${roomId}">
							<input type="button" class="btn btn-default" value="キャンセル" />
						</a>
						<!-- ルーム参加 -->
						<button id="send" class="btn btn-default" name="btn" value="join">参加する</button>
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

	<script src="js/joinmember.js"></script>
</body>
</html>
