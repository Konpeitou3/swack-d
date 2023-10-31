package servlet;

import static parameter.Messages.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDAO;
import exception.SwackException;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// signup.jspに遷移
		request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 値を受け取る
		String username = request.getParameter("userName");
		System.out.println(username);

		String mailaddress = request.getParameter("mailAddress");
		System.out.println(mailaddress);

		String password = request.getParameter("password");
		System.out.println(password);

		//入力値判定
		StringBuilder errorMsg = new StringBuilder();

		if (username == null || username.length() == 0) {
			errorMsg.append("ユーザ名が入力されていません<br>");
		}
		if (mailaddress == null || mailaddress.length() == 0) {
			errorMsg.append("メールアドレスが入力されていません<br>");
		}
		if (password == null || password.length() == 0) {
			errorMsg.append("パスワードが入力されていません<br>");
		}

		if (errorMsg.length() > 0) {
			// エラー
			request.setAttribute("errorMsg", errorMsg.toString());
			request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
			return;
		}
		//DAOを使用
		try {
			UsersDAO usersDao = new UsersDAO();
			int result = usersDao.insert(username, mailaddress, password);
			if (result == 1) {
				request.setAttribute("successMsg", NEW_USERS_CREATE_SUCCSESS);
				request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute("errorMsg", ERR_USERS_PARAM_MISTAKE);
				request.getRequestDispatcher("WEB-INF/jsp/signup.jsp").forward(request, response);
				return;
			}
		} catch (SwackException e) {
			// エラー処理
			e.printStackTrace();
			errorMsg.append("処理が正常に動きませんでした");
			request.getRequestDispatcher("WEB-INF/jsp/signup.jsp").forward(request, response);
			return;
		}

	}

}
