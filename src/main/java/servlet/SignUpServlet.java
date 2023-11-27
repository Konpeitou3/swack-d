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
		StringBuilder errorMsg = new StringBuilder();

		// 値を受け取る
		String userName = request.getParameter("userName");
		//サニタイジング
		String tryUserName = userName.replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;")
				.replace(">", "&gt;").replace("'", "&#39;");
		boolean b1 = tryUserName.contains("&amp;");
		boolean b2 = tryUserName.contains("&quot;");
		boolean b3 = tryUserName.contains("&lt;");
		boolean b4 = tryUserName.contains("&gt;");
		boolean b5 = tryUserName.contains("&#39;");

		//ユーザー名チェック
		if (b1 == true || b2 == true || b3 == true || b4 == true || b5 == true) {
			System.out.println("クロスサイトスクリプティングの可能性あり");
			errorMsg.append("不正なユーザー名です。変更してください。");
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
		}

		String mailAddress = request.getParameter("mailAddress");
		String tryMailAddress = mailAddress.replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;")
				.replace(">", "&gt;").replace("'", "&#39;");
		boolean b1_2 = tryUserName.contains("&amp;");
		boolean b2_2 = tryUserName.contains("&quot;");
		boolean b3_2 = tryUserName.contains("&lt;");
		boolean b4_2 = tryUserName.contains("&gt;");
		boolean b5_2 = tryUserName.contains("&#39;");

		//メールアドレスチェック
		if (b1_2 == true || b2_2 == true || b3_2 == true || b4_2 == true || b5_2 == true) {
			System.out.println("クロスサイトスクリプティングの可能性あり");
			errorMsg.append("不正なメールアドレスです。変更してください。");
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
		}

		String password = request.getParameter("password");
		String tryPassword = password.replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;")
				.replace(">", "&gt;").replace("'", "&#39;");
		boolean b1_3 = tryUserName.contains("&amp;");
		boolean b2_3 = tryUserName.contains("&quot;");
		boolean b3_3 = tryUserName.contains("&lt;");
		boolean b4_3 = tryUserName.contains("&gt;");
		boolean b5_3 = tryUserName.contains("&#39;");

		//パスワードチェック
		if (b1_3 == true || b2_3 == true || b3_3 == true || b4_3 == true || b5_3 == true) {
			System.out.println("クロスサイトスクリプティングの可能性あり");
			errorMsg.append("不正なパスワードです。変更してください。");
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
		}

		//入力値判定

		if (tryUserName == null || tryUserName.length() == 0) {
			errorMsg.append("ユーザ名が入力されていません<br>");
		}
		if (tryMailAddress == null || tryMailAddress.length() == 0) {
			errorMsg.append("メールアドレスが入力されていません<br>");
		}
		//パスワードチェック
		if (tryPassword == null || tryPassword.length() == 0) {
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
				if ((tryMailAddress.equals(user.getMailAddress())) == true) {
					//入力されたメールアドレスがすでに登録済みの場合
					System.out.println("エラー");
					request.setAttribute("errorMsg", ERR_USERS_ISREGISTERED);
					//signup.jspにリダイレクト
					request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
					return;
				}
			}
			int result = usersDao.insert(tryUserName, tryMailAddress, tryPassword);
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
