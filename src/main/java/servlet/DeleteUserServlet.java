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
		HttpSession session = request.getSession();
		String roomId = (String) session.getAttribute("roomId");
		response.sendRedirect("MainServlet?roomId=" + roomId);
	}

}
