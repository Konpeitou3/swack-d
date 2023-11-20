package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.SwackException;
import model.ChatModel;

/**
 * Servlet implementation class DeleteMessageServlet
 */
@WebServlet("/DeleteMessageServlet")
public class DeleteMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteMessageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//チャットログIDを画面から受け取る
		String Id = request.getParameter("chatLogId");
		int chatlogId = Integer.parseInt(Id);

		try {
			//コメントを削除する
			new ChatModel().deleteChatlog(chatlogId);

		} catch (SwackException e) {
			e.printStackTrace();
		}
		// Main.jspに遷移
		request.getRequestDispatcher("MainServlet").forward(request, response);
		return;
	}

}
