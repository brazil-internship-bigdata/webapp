package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Company;
import dao.CompanyDao;
import dao.DAOFactory;
import forms.Form;
import forms.LoginForm;

@SuppressWarnings("serial")
@WebServlet("/login")
public class Login extends ServletWithConstants {

	private CompanyDao companyDao;

	public void init() throws ServletException {
		this.companyDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCompanyDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (adminRequest(request)) {

			response.sendRedirect(ADMIN);

		} else {
			LoginForm form = new LoginForm(companyDao);

			Company company = form.loginCompany(request);

			if (form.getErrors().isEmpty()) {
				session.setAttribute(ATT_COMPANY_SESSION, company);

				response.sendRedirect(HOMEPAGE);
			} else {
				session.setAttribute(ATT_COMPANY_SESSION, null);

				request.setAttribute(ATT_FORM, form);
				request.setAttribute(ATT_COMPANY, company);

				this.getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
			}
		}

	}

	private boolean adminRequest(HttpServletRequest request) {
		boolean result = request.getParameter(Form.FIELD_COMPANY_NAME).equals("admin")
				&& request.getParameter(Form.FIELD_PASSWORD_COMPANY).equals("admin");

		System.out.println(result);

		return result;
	}
}