package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/homepage")
public class Homepage extends HttpServlet {

	public static final String	ATT_SESSION_USER	= "sessionUtilisateur";

	public static final String	ATT_CLIENT			= "client";
	public static final String	ATT_FORM			= "form";

	public static final String	VUE					= "/WEB-INF/homepage.jsp";

	public static final String	URL_REDIRECTION		= "/login";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute(ATT_SESSION_USER) == null) {
			System.out.println("redirect");
			response.sendRedirect(this.getServletContext().getContextPath() + URL_REDIRECTION);
		} else {
			System.out.println(httpSession);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}

	}
}