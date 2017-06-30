package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Company;
import dao.CompanyDao;
import dao.DAOFactory;
import forms.LoginForm;

@SuppressWarnings("serial")
@WebServlet("/login")
public class Login extends HttpServlet implements ServletConstants {

	private CompanyDao companyDao;

	public void init() throws ServletException {
		this.companyDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCompanyDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginForm form = new LoginForm(companyDao);

		Company company = form.loginCompany(request);

		HttpSession session = request.getSession();

		if (form.getErrors().isEmpty()) {
			session.setAttribute(ATT_COMPANY_SESSION, company);

			System.out.println(company.getCompanyName());

			response.sendRedirect(HOMEPAGE);
		} else {
			session.setAttribute(ATT_COMPANY_SESSION, null);

			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_COMPANY, company);

			this.getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
		}

	}
}