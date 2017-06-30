package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Company;
import dao.CompanyDao;
import dao.DAOFactory;
import forms.SignUpForm;

@SuppressWarnings("serial")
@WebServlet("/signup")
public class Signup extends HttpServlet implements ServletConstants {

	private CompanyDao companyDao;

	// TODO liste déroulante dans la JSP

	public void init() throws ServletException {
		this.companyDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCompanyDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(SIGNUP_VIEW).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SignUpForm form = new SignUpForm(companyDao);

		Company company = form.signUpCompany(request);

		request.setAttribute(ATT_FORM, form);

		if (!form.getErrors().isEmpty()) {
			request.setAttribute(ATT_COMPANY, company);
		} else {
			request.setAttribute(ATT_COMPANY, null);
		}
		this.getServletContext().getRequestDispatcher(SIGNUP_VIEW).forward(request, response);

	}

}