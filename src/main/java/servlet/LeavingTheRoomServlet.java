package servlet;

import static parameter.Messages.*;

import java.io.IOException;

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
 * Servlet implementation class WithdrawalServret
 */
@WebServlet("/LeavingTheRoomServlet")
public class LeavingTheRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LeavingTheRoomServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String roomId = request.getParameter("roomId");

		HttpSession session = request.getSession();
		session.setAttribute("roomId", roomId);
		User user = (User) session.getAttribute("user");
		String userId = user.getUserId();

		try {
			new JoinRoomModel().LeavingTheRoom(roomId, userId);
		} catch (SwackException e1) {
			e1.printStackTrace();
			request.setAttribute("errorMsg", ERR_SYSTEM);
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			return;
		}

	}

}
