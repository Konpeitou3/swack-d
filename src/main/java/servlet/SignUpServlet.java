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
	 * userName  ユーザー名
	 * mailAddress  メールアドレス
	 * password  パスワード
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 値を受け取る
		String userName = request.getParameter("userName");
		System.out.println(userName);

		String mailAddress = request.getParameter("mailAddress");
		System.out.println(mailAddress);

		String password = request.getParameter("password");
		System.out.println(password);

		//入力値判定
		StringBuilder errorMsg = new StringBuilder();

		if (userName == null || userName.length() == 0) {
			errorMsg.append("ユーザ名が入力されていません<br>");
		}
		if (mailAddress == null || mailAddress.length() == 0) {
			errorMsg.append("メールアドレスが入力されていません<br>");
		}
		//パスワードチェック
		if (password == null || password.length() == 0) {
			errorMsg.append("パスワードが入力されていません<br>");
		}
		if (errorMsg.length() > 0) {
			// エラー
			request.setAttribute("errorMsg", errorMsg.toString());
			//signup.jspにリダイレクト
			request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
			return;
		}
		//メールアドレスチェック
		try {
			UsersDAO usersDao = new UsersDAO();
			List<User> mailaddressList = usersDao.getMailAddressList();
			for (User user : mailaddressList) {
				System.out.println(user.getMailAddress());
				if ((mailAddress.equals(user.getMailAddress())) == true) {
					//入力されたメールアドレスがすでに登録済みの場合
					System.out.println("エラー");
					request.setAttribute("errorMsg", ERR_USERS_ISREGISTERED);
					//signup.jspにリダイレクト
					request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
					return;
				}
			}
			int result = usersDao.insert(userName, mailAddress, password);
			if (result == 1) {
				//正常にアカウントをDBに登録できたら
				//login.jspに遷移
				request.setAttribute("successMsg", NEW_USERS_CREATE_SUCCSESS);
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return;
			} else {
				//アカウントを登録できなかったら
				//signup.jspにリダイレクト
				request.setAttribute("errorMsg", NEW_USERS_CREATE_SUCCSESS);
				request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
			}

		} catch (SwackException e1) {
			// 自動生成された catch ブロック
			e1.printStackTrace();
			errorMsg.append("処理が正常に動きませんでした");
			//signup.jspにリダイレクト
			request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
			return;
		}

	}

}
