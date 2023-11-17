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

		//TODO アカウントロック中のリストをJSPに渡す
		UserModel userModel = new UserModel();
		try {
			List<User> lockeduserlist = userModel.lockedUserList();
			System.out.println(lockeduserlist);
			request.setAttribute("lockeduserlist", lockeduserlist);
		} catch (SwackException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", ERR_SYSTEM);
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			return;
		}
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
		UserModel userModel = new UserModel();
		try {
			userModel.updateLockedFalse(userId);
		} catch (SwackException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", ERR_SYSTEM);
			request.getRequestDispatcher("/WEB-INF/jsp/unlock.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/WEB-INF/jsp/unlock.jsp").forward(request, response);
	}

}
