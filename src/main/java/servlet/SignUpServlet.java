package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDAO;
import exception.SwackException;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// signup.jspに遷移
		request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 値を受け取る
		String username = request.getParameter("userName");
		String mailaddress = request.getParameter("mailAddress");
		String password = request.getParameter("password");

		//DAOを使用
		try {
			UsersDAO usersDao = new UsersDAO();
			usersDao.insert(username, mailaddress, password);

		} catch (SwackException e) {
			// エラー処理
			e.printStackTrace();
			response.sendRedirect("signup.jsp");
			return;
		}

		response.sendRedirect("LoginServlet");
		return;

	}

}
