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
 * Servlet implementation class JoinMemberServlet
 */
@WebServlet("/JoinMemberServlet")
public class JoinMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JoinMemberServlet() {
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
		HttpSession session = request.getSession();
		session.setAttribute("roomId", roomId);
		//  招待することができる人の一覧を取得
		User user = new User();
		UserModel userModel = new UserModel();
		try {
			List<User> userList = userModel.getUserList(user.getUserId());
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("/WEB-INF/jsp/joinmember.jsp").forward(request, response);
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
		// 画面から取得
		String[] selectUser = request.getParameterValues("selectUser");

		//セッションからルームIDを取得
		HttpSession session = request.getSession();
		String roomId = (String) session.getAttribute("roomId");

		//選択された人数分Incertを実行
		for (String userId : selectUser) {
			JoinRoomModel joinRoomModel = new JoinRoomModel();
			int result;
			try {
				result = joinRoomModel.joinRoom(roomId, userId);
				if (result != 1) {
					System.out.println("エラーが発生");
				}
			} catch (SwackException e) {
				// 自動生成された catch ブロック
				e.printStackTrace();
			}

		}
		//GET処理にリダイレクト
		response.sendRedirect("MainServlet?roomId=" + roomId);

	}

}
