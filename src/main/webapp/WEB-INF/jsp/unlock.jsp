<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>Swack - アカウントロック解除</title>

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
    <link rel="stylesheet" href="css/unlock.css" />
  </head>

  <body>
    <div class="container form-container">
      <div class="row">
        <div class="col-md-12 member-form">
          <h3>アカウントロック解除</h3>

          <form action="AccountUnrockServlet" method="post" name="userId" value="${userId}">
            <input type="hidden" name="roomId" value="${roomId}" />
            <div class="form-group">
              <label class="control-label">ロックを解除するユーザーを選ぶ</label>
              <c:if test="${empty lockeduserlist}">
              <span class="not-found-user"> ※ロックがかかっているユーザーがいません</span>
              </c:if>
              <select id="users" class="form-select" name="userId" multiple>
              <c:forEach var="user" items="${lockeduserlist}">
  				<option value="${user.userName}"><c:out value="${user.userId}"/></option>            
              </c:forEach>
              </select>
              <span class="users-note"
                >ロックを解除するアカウントを選んでください。</span
              >
            </div>

            <div class="member-form-btn">
              <a href="MainServlet?roomId=${roomId}"
                ><input
                  type="button"
                  class="btn btn-default"
                  value="キャンセル"
              /></a>
              <button id="send" class="btn btn-default" name="btn" value="join">
                実行
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
