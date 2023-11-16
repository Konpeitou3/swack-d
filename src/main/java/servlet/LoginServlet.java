package servlet;

import static parameter.Messages.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.FailedLog;
import bean.User;
import exception.SwackException;
import model.FailedLogModel;
import model.LoginModel;
import model.UserModel;

/**
 * ログイン処理を実行するServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String roomId = (String) session.getAttribute("roomId");
		User user = (User) session.getAttribute("user");
		if (user != null) {
			if (roomId == null) {
				roomId = "R0000";
				response.sendRedirect("MainServlet?roomId=" + roomId);
				return;
			} else {
				response.sendRedirect("MainServlet?roomId=" + roomId);
				return;
			}
		} else {
			// 未ログインの場合は、ログイン画面に遷移
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// パラメータ取得
		String mailAddress = request.getParameter("mailAddress");
		String password = request.getParameter("password");

		// パラメータチェック
		StringBuilder errorMsg = new StringBuilder();
		if (mailAddress == null || mailAddress.length() == 0) {
			errorMsg.append("メールアドレスが入っていません<br>");
		}
		if (password == null || password.length() == 0) {
			errorMsg.append("パスワードが入っていません<br>");
		}
		if (errorMsg.length() > 0) {
			// エラー
			request.setAttribute("errorMsg", errorMsg.toString());
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			return;
		}

		try {
			User user1 = new LoginModel().mailAddressCheck(mailAddress);

			if (user1 == null) {
				request.setAttribute("errorMsg", NEW_USERS_SELECT_MISTAKE);
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return;
			} else {
				try {
					// ログインチェック
					User user2 = new LoginModel().checkLogin(mailAddress, password);
					System.out.println(user2);
					UserModel userModel = new UserModel();
					//アカウントロックチェック
					if (user1.isLocked() == true) {
						//アカウントロックのためログイン失敗
						request.setAttribute("errorMsg", ACCOUNT_LOCKED);
						request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
						return;
					}
					if (user2 == null) {
						// 認証失敗
						//ログイン失敗テーブルに追加
						//メールアドレスだけでuser情報の取得をするように改装、取れなかった場合と取れた場合
						// 取れなかった場合はエラー　取れた上でパスワードが間違っている場合はaccountロック機構を動かす
						FailedLogModel failedLogModel = new FailedLogModel();
						failedLogModel.insert(user1.getUserId());
						//ログインテーブルの件数取得、5件取得したらaccountロック
						LoginModel loginModel = new LoginModel();
						List<FailedLog> LogCount = loginModel.lastLoginCheck(user1.getUserId());
						System.out.println(LogCount);
						System.out.println(LogCount.size());
						if (LogCount.size() == 5) {
							userModel.updateLockedTrue(user1.getUserId());
						}
						request.setAttribute("errorMsg", PASSWORD_MISTAKE);
						request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
						return;
					}

					//最終ログイン更新
					userModel.updateLastLogin(user2.getUserId());
					// 認証成功(ログイン情報をセッションに保持)
					HttpSession session = request.getSession();
					session.setAttribute("user", user2);
					response.sendRedirect("MainServlet?roomId=R0000");

					return;

				} catch (SwackException e) {
					e.printStackTrace();
					request.setAttribute("errorMsg", ERR_SYSTEM);
					request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
					return;
				}
			}

		} catch (SwackException e1) {
			request.setAttribute("errorMsg", ERR_SYSTEM);
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			return;
		}

	}

}