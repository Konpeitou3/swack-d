<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>Swack - 管理者画面</title>

    <link rel="shortcut icon" href="images/favicon.ico" />

    <!-- CDN : Bootstrap CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    
    <!-- フォントを使用するため外部のサイトの読み込み -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@600&display=swap" rel="stylesheet">
    

    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="css/joinmember.css" />
  </head>

  <body>
    <div class="container form-container">
      <div class="row">
        <div class="col-md-12 member-form">
          <h3>ルームからユーザーを退会させる</h3>

          <form action="JoinMemberServlet" method="post">
            <input type="hidden" name="roomId" value="${roomId}" />
            <div class="form-group">
              <label class="control-label">退会させるユーザーを選ぶ </label>
              <c:if test="${empty usersList}">
              <span class="not-found-user"> ※退会させられるユーザーがいません</span>
              </c:if>
              <select id="users" class="form-select" name="selectUser" multiple>
              <c:forEach var="user" items="${usersList}">
  				<option value="${user.userId}">${user.userName}</option>            
              </c:forEach>
              </select>
              <span class="users-note">
              このルームから退会させるユーザーを選択してください
              </span>
            </div>

            <div class="member-form-btn">
              <a href="MainServlet?roomId=${roomId}"
                ><input
                  type="button"
                  class="btn btn-default"
                  value="キャンセル"
              /></a>
              <button id="send" class="btn btn-default" name="btn" value="join">
                退会させる
              </button>
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
      crossorigin="anonymous"
    ></script>

    <script src="js/joinmember.js"></script>
  </body>
</html>
