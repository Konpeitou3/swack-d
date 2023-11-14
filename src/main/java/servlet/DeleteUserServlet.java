package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.User;
import exception.SwackException;
import model.JoinRoomModel;
import model.RoomAdminModel;

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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String roomId = request.getParameter("roomId");
		String userId = user.getUserId();
		boolean Admin = false;

		JoinRoomModel joinRoomModel = new JoinRoomModel();
		RoomAdminModel roomAdminModel = new RoomAdminModel();

		try {
			List<User> notAdminUserList = joinRoomModel.getNotAdminUserList(roomId);
			List<Admin> RoomAdminList = roomAdminModel.getRoomAdminList(roomId, userId);
			for (Admin admin : RoomAdminList) {
				if (userId.equals(admin.getUserId())) {
					//アドミン判定
					Admin = true;
					// アドミン以外のユーザーIDのリストをJSPに値を渡す
					request.setAttribute("notAdminUserList", notAdminUserList);
				}
			}
			//アドミン判定を渡す
			request.setAttribute("Admin", Admin);
		} catch (SwackException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("deleteuser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
