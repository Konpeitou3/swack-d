package servlet;

import static parameter.Messages.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import exception.SwackException;
import model.UserModel;

/**
 * Servlet implementation class PasswordUpdateServlet
 */
@WebServlet("/PasswordUpdateServlet")
public class PasswordUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasswordUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 未ログインの場合は、パスワード変更画面に遷移
		request.getRequestDispatcher("/WEB-INF/jsp/passwordupdate.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// パラメータ取得
		String inputMailAddress = request.getParameter("mailAddress");
		String inputPassword = request.getParameter("password");
		System.out.println(inputPassword);
		System.out.println(inputMailAddress);

		// パラメータチェック
		StringBuilder errorMsg = new StringBuilder();
		if (inputMailAddress == null || inputMailAddress.length() == 0) {
			errorMsg.append("メールアドレスが入っていません<br>");
		}
		if (inputPassword == null || inputPassword.length() == 0) {
			errorMsg.append("パスワードが入っていません<br>");
		}
		if (errorMsg.length() > 0) {
			// エラー
			request.setAttribute("errorMsg", errorMsg.toString());
			request.getRequestDispatcher("/WEB-INF/jsp/passwordupdate.jsp").forward(request, response);
			return;
		}

		// 処理
		try {
			// ログインチェック
			//TODO モデル書き換え
			List<User> userList = new UserModel().getAllUserList();

			//			user.getPassward()
			for (User user : userList) {
				System.out.println(user.getMailAddress());
				System.out.println(user.getPassword());

				if (inputMailAddress.equals(user.getMailAddress())) {
					if (inputPassword.equals(user.getPassword())) {
						//現在のパスワードと入力されたパスワードが一緒
						request.setAttribute("errorMsg", PASSWORD_UPDATE_ERROR);
						request.getRequestDispatcher("/WEB-INF/jsp/passwordupdate.jsp").forward(request, response);
					} else {
						new UserModel().updatePassword(inputPassword, inputMailAddress);
						request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
						return;
						//エラーがあった場合
					}
					if (errorMsg.length() > 0) {
						// エラー
						request.setAttribute("errorMsg", errorMsg.toString());
						request.getRequestDispatcher("/WEB-INF/jsp/passwordupdate.jsp").forward(request, response);
						return;
					}

				}

			}
			request.setAttribute("errorMsg", NEW_USERS_SELECT_MISTAKE);
			request.getRequestDispatcher("/WEB-INF/jsp/passwordupdate.jsp").forward(request, response);

		} catch (SwackException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", ERR_SYSTEM);
			request.getRequestDispatcher("/WEB-INF/jsp/passwordupdate.jsp").forward(request, response);
			return;
		}
	}

}
