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

import bean.User;
import exception.SwackException;
import model.JoinRoomModel;
import model.UserModel;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteUserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//現在のルームIDを取得
		String roomId = request.getParameter("roomId");

		//ルームIDをセッションに保存
		HttpSession session = request.getSession();
		session.setAttribute("roomId", roomId);

		JoinRoomModel joinRoomModel = new JoinRoomModel();

		try {
			List<User> notAdminUserList = joinRoomModel.getNotAdminUserList(roomId);

			// アドミン以外のユーザーIDのリストをJSPに値を渡す
			request.setAttribute("notAdminUserList", notAdminUserList);

		} catch (SwackException e) {
			e.printStackTrace();
		}
		//ルーム退会画面に遷移
		request.getRequestDispatcher("/WEB-INF/jsp/deleteuser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 画面からルーム退会させるユーザーを取得
		String[] selectUser = request.getParameterValues("selectUser");

		//セッションからルームIDを取得
		HttpSession session = request.getSession();
		String roomId = (String) session.getAttribute("roomId");

		//選択された人数分ルーム退会を実行
		for (String userId : selectUser) {
			UserModel userModel = new UserModel();
			int result;
			try {
				result = userModel.delete(userId);
				if (result != 1) {
					request.setAttribute("errorMsg", ERR_SYSTEM);
					request.getRequestDispatcher("/WEB-INF/jsp/deleteuser.jsp").forward(request, response);
					return;
				}
			} catch (SwackException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", ERR_SYSTEM);
				request.getRequestDispatcher("/WEB-INF/jsp/deleteuser.jsp").forward(request, response);
				return;
			}

		}
		//ルームIDを渡して、メイン画面に遷移
		response.sendRedirect("MainServlet?roomId=" + roomId);
	}

}
