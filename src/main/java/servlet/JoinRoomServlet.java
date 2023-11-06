package servlet;

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
import model.OtherRoomListModel;

/**
 * Servlet implementation class JoinRoomServlet
 */
@WebServlet("/JoinRoomServlet")
public class JoinRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JoinRoomServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
