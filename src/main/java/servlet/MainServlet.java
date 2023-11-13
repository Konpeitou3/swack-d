package servlet;

import static parameter.Messages.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.User;
import exception.SwackException;
import model.ChatModel;
import model.JoinRoomModel;
import model.RoomAdminModel;

@WebServlet("/MainServlet")
public class MainServlet extends LoginCheckServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 画面から取得
		String roomId = request.getParameter("roomId");
		if (roomId == null) {
			// 初期ルームをeveryoneにする
			roomId = "R0000";
		}
		String userId = request.getParameter("userId");

		JoinRoomModel joinRoomModel = new JoinRoomModel();
		//管理者の場合管理者以外のリスト取得
		try {
			List<User> notAdminUserList = joinRoomModel.getNotAdminUserList(roomId);
			System.out.println(notAdminUserList);

			//管理者確認
			RoomAdminModel roomAdminModel = new RoomAdminModel();
			try {
				List<Admin> RoomAdminList = roomAdminModel.getRoomAdminList(roomId, userId);

				for (Admin admin : RoomAdminList) {
					if (userId.equals(admin.getUserId())) {
						request.setAttribute("notAdminUserList", notAdminUserList);
						request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
					}
				}

			} catch (SwackException e1) {
				e1.printStackTrace();
				request.setAttribute("errorMsg", ERR_SYSTEM);
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return;
			}

		} catch (SwackException e1) {
			e1.printStackTrace();
			request.setAttribute("errorMsg", ERR_SYSTEM);
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//画面から取得
		String roomId = request.getParameter("roomId");
		String message = request.getParameter("message");

		// ログイン情報から取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		try {
			new ChatModel().saveChatLog(roomId, user.getUserId(), message);
		} catch (SwackException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", ERR_SYSTEM);
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			return;
		}
		//セッションに閲覧しているルーム情報を保存
		HttpSession save_session = request.getSession();
		save_session.setAttribute("roomId", roomId);

		//GET処理にリダイレクト
		response.sendRedirect("MainServlet?roomId=" + roomId);
	}

}
