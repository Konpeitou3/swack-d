package servlet;

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
		User user = (User) session.getAttribute("user");

		JoinRoomModel joinRoomModel = new JoinRoomModel();

		try {
			List<User> notAdminUserList = joinRoomModel.getNotAdminUserList(roomId);

			// アドミン以外のユーザーIDのリストをJSPに値を渡す
			request.setAttribute("notAdminUserList", notAdminUserList);
			System.out.println(notAdminUserList);

		} catch (SwackException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("deleteuser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 画面から取得
		String[] selectUser = request.getParameterValues("selectUser");

		//セッションからルームIDを取得
		HttpSession session = request.getSession();
		String roomId = (String) session.getAttribute("roomId");

		//選択された人数分Incertを実行
		for (String userId : selectUser) {
			UserModel userModel = new UserModel();
			int result;
			try {
				result = userModel.delete(userId);
				if (result != 1) {
					System.out.println("エラーが発生");
				}
			} catch (SwackException e) {
				// 自動生成された catch ブロック
				e.printStackTrace();
			}

		}

		response.sendRedirect("MainServlet?roomId=" + roomId);
	}

}
