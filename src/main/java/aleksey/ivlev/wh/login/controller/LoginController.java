package aleksey.ivlev.wh.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import aleksey.ivlev.wh.login.services.AccountVerification;
import aleksey.ivlev.wh.model.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		try {
			InitialContext initContext = new InitialContext();

			Context context = (Context) initContext.lookup("java:comp/env");

			ds = (DataSource) context.lookup("jdbc/warehouse");
		} catch (NamingException e) {
			throw new ServletException();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			request.getRequestDispatcher("/view/index.jsp").forward(request,
					response);
		} else if (action.equals("login")) {
			request.setAttribute("email", "");
			request.setAttribute("password", "");
			request.setAttribute("message", "");			
			
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			return;
		}

		Connection conn = null;

		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServletException();
		}

		AccountVerification account = new AccountVerification(conn);

		if (action.equals("dologin")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			User user = new User(email, password);

			request.setAttribute("email", user.getEmail());
			request.setAttribute("password", "");

			try {
				if (account.login(email, password)) {
					request.getRequestDispatcher("/view/index.jsp").forward(
							request, response);
				} else {
					request.setAttribute("message",
							"email address or password are incorrect");
					request.getRequestDispatcher("/login.jsp").forward(request,
							response);
				}
			} catch (SQLException e) {
				request.setAttribute("message",
						"Database error: please try again later.");
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			}

		}
	}

}
