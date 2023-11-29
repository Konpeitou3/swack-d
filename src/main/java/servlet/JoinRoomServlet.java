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

import bean.Room;
import bean.User;
import exception.SwackException;
import model.JoinRoomModel;
import model.OtherRoomListModel;

/**
 * Servlet implementation class JoinRoomServlet
 */
@WebServlet("/JoinRoomServlet")
public class JoinRoomServlet extends LoginCheckServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッションからユーザ情報を取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// 参加することのできるルーム一覧を取得

		OtherRoomListModel otherRoomListModel = new OtherRoomListModel();
		try {
			List<Room> roomList = otherRoomListModel.getOtherRoomList(user.getUserId());
			request.setAttribute("roomList", roomList);
			request.getRequestDispatcher("/WEB-INF/jsp/joinroom.jsp").forward(request, response);
			return;
		} catch (SwackException e) {
			//  自動生成された catch ブロック
			e.printStackTrace();
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String roomId = request.getParameter("roomId");
		String select_roomId = request.getParameter("selectRoom");

		//セッションからユーザー情報を取得
		HttpSession get_session = request.getSession();
		User user = (User) get_session.getAttribute("user");
		String roomId = (String) get_session.getAttribute("roomId");

		JoinRoomModel joinRoomModel = new JoinRoomModel();
		try {
			// 新規ルーム参加を実行する
			int result = joinRoomModel.joinRoom(select_roomId, user.getUserId());
			System.out.println(result);
		} catch (SwackException e) {
			e.printStackTrace();
			// ログイン失敗エラーを出力する
			request.setAttribute("errorMsg", ERR_LOGIN_PARAM_MISTAKE);
			// 再起処理
			request.getRequestDispatcher("/WEB-INF/jsp/joinroom.jsp").forward(request, response);
			return;
		}
		//GET処理にリダイレクト
		response.sendRedirect("MainServlet?roomId=" + roomId);
	}

}
