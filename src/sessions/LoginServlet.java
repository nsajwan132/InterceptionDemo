package sessions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.AppConstant;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String user = "admin";
	private final String password = "admin";

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get request parameters for username and passwd
		String username = request.getParameter("username");
		String passwd = request.getParameter("password");
		HttpSession session = request.getSession();
		if (user.equals(username) && password.equals(passwd)) {
			session.setAttribute("user", username);
			// setting session to expire in 1 hour
			session.setMaxInactiveInterval(60 * 60);

			Cookie userName = new Cookie("user", user);
			userName.setMaxAge(60 * 60);
			response.addCookie(userName);
			request.setAttribute("error", "false");
			response.sendRedirect("LoginSuccess.jsp");
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/"+AppConstant.HOME_PAGE_URL);
			PrintWriter out = response.getWriter();
			request.setAttribute("error", "true");
			rd.include(request, response);
		}
	}

}
