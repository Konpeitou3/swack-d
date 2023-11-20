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
 * Servlet implementation class AccountUnrock
 */
@WebServlet("/AccountUnrockServlet")
public class AccountUnrockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountUnrockServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// インスタンス生成
		UserModel userModel = new UserModel();

		try {
			// アカウントロックされているユーザーを受け取る
			List<User> lockeduserlist = userModel.lockedUserList();

			// アカウントロック中のリストをJSPに渡す
			request.setAttribute("lockeduserlist", lockeduserlist);

		} catch (SwackException e) {
			e.printStackTrace();

			//システムエラー表示
			request.setAttribute("errorMsg", ERR_SYSTEM);

			//メイン画面に遷移
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			return;
		}
		//リストを受け取れた場合、アカウントロック解除画面に遷移
		request.getRequestDispatcher("/WEB-INF/jsp/unlock.jsp").forward(request, response);
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ユーザーIDの取得
		String userId = request.getParameter("userId");

		//インスタンス生成
		UserModel userModel = new UserModel();

		try {
			// アカウントロックを解除する
			userModel.updateLockedFalse(userId);
		} catch (SwackException e) {
			e.printStackTrace();

			// システムエラー表示
			request.setAttribute("errorMsg", ERR_SYSTEM);

			// アカウントロック画面表示
			request.getRequestDispatcher("/WEB-INF/jsp/unlock.jsp").forward(request, response);
			return;
		}

		// アカウントロック画面表示
		request.getRequestDispatcher("/WEB-INF/jsp/unlock.jsp").forward(request, response);
	}

}
