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
import bean.ChatLog;
import bean.Room;
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		System.out.println(user);
		String userId = user.getUserId();
		boolean Admin = false;

		JoinRoomModel joinRoomModel = new JoinRoomModel();
		//管理者の場合管理者以外のリスト取得
		try {
			List<User> notAdminUserList = joinRoomModel.getNotAdminUserList(roomId);

			//管理者確認
			RoomAdminModel roomAdminModel = new RoomAdminModel();
			try {
				List<Admin> RoomAdminList = roomAdminModel.getRoomAdminList(roomId, userId);

				ChatModel chatModel = new ChatModel();
				Room room = chatModel.getRoom(roomId, user.getUserId());
				List<Room> roomList = chatModel.getRoomList(user.getUserId());
				List<Room> directList = chatModel.getDirectList(user.getUserId());
				List<ChatLog> chatLogList = chatModel.getChatlogList(roomId);

				// JSPに値を渡す
				request.setAttribute("room", room);
				request.setAttribute("roomList", roomList);
				request.setAttribute("directList", directList);
				request.setAttribute("chatLogList", chatLogList);

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
				request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);

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
