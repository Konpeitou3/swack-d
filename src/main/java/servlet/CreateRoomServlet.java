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
import model.CreateRoomModel;
import model.JoinRoomModel;

/**
 * Servlet implementation class CreateRoomServlet
 */
@WebServlet("/CreateRoomServlet")
public class CreateRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateRoomServlet() {
		super();
		// Auto-generated constructor stub
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
			userList = new CreateRoomModel().getMember(user.getUserId());
		} catch (SwackException e) {
			e.printStackTrace();
			return;
		}
		//userListをリクエストに保存
		request.setAttribute("usersList", userList);
		//createroom.jspにフォワード
		request.getRequestDispatcher("WEB-INF/jsp/createroom.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
		/*if (selectUser.length == 1) {
			directed = true;
		} else {
			directed = false;
		}*/
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
				request.setAttribute("errorMsg", CREATE_ROOM_ERROR);
				request.getRequestDispatcher("WEB-INF/jsp/createroom.jsp").forward(request, response);
				return;
			} else {
				//request.setAttribute("succsessMsg", CREATE_ROOM_SUCCESS);
				//GET処理にリダイレクト

				//TODO while selectUser分回す
				for (String selectuser : selectUser) {
					new JoinRoomModel().joinRoom(roomId, selectuser);
				}
				//TODO joinRoomModel().joinRoom(roomid,userid)を実行する
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
