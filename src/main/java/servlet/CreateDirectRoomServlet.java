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
import model.CreateRoomModel;
import model.JoinRoomModel;
import model.UserModel;

/**
 * Servlet implementation class CreateDirectRoomServlet
 */
@WebServlet("/CreateDirectRoomServlet")
public class CreateDirectRoomServlet extends LoginCheckServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateDirectRoomServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roomId = request.getParameter("roomId");

		//ルームIDをセッションに保存
		HttpSession room_session = request.getSession();
		room_session.setAttribute("roomId", roomId);
		// main.jspから呼び出し
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// userList取得
		List<User> userList;
		try {
			userList = new UserModel().getUserList(user.getUserId());
		} catch (SwackException e) {
			e.printStackTrace();
			return;
		}
		//userListをリクエストに保存
		request.setAttribute("usersList", userList);
		//createroom.jspにフォワード
		request.getRequestDispatcher("WEB-INF/jsp/createroom.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// createroom.jspから呼び出し

		// 値受け取り(ルーム名)
		String roomName = request.getParameter("roomName");

		// 値受け取り（招待先）
		String[] selectUser = request.getParameterValues("selectUser");
		System.out.println(selectUser);

		//値受け取り（ユーザーID）
		HttpSession session = request.getSession();
		String roomId = (String) session.getAttribute("roomId");
		System.out.println(roomId);
		User user = (User) session.getAttribute("user");
		String createduserid = user.getUserId();

		//値受け取り（フラグ）
		String flag = request.getParameter("setting");
		Boolean directed = false;
		Boolean privated = null;
		if (flag == null) {
			privated = false;
		} else {
			privated = true;
		}

		//DAO使用
		try {
			CreateRoomModel createRoomModel = new CreateRoomModel();
			int result = createRoomModel.createRoom(roomName, createduserid, directed, privated);
			if (result != 1) {
				request.getRequestDispatcher("WEB-INF/jsp/createroom.jsp").forward(request, response);
				return;
			} else {
				//request.setAttribute("succsessMsg", CREATE_ROOM_SUCCESS);
				//GET処理にリダイレクト

				String maxroomid = createRoomModel.RoomSelect();
				System.out.println(maxroomid);
				// while selectUser分回す
				System.out.println(selectUser);
				for (String selectuser : selectUser) {
					System.out.println(selectuser);

					new JoinRoomModel().joinRoom(maxroomid, selectuser);

				}
				// joinRoomModel().joinRoom(roomid,userid)を実行する
				response.sendRedirect("MainServlet?roomId=" + roomId);
				return;
			}
		} catch (SwackException e) {
			//  自動生成された catch ブロック
			e.printStackTrace();
			return;
		}

	}

}
