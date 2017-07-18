package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Company;
import dao.CompanyDao;
import dao.DAOFactory;
import forms.LoginForm;

@SuppressWarnings("serial")
@WebServlet("/loginAPI")
public class LoginAPI extends ServletWithConstants {

	private CompanyDao companyDao;

	public void init() throws ServletException {
		this.companyDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCompanyDao();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("company : " + request.getParameter("company_name"));
		System.out.println("password : " + request.getParameter("password_company"));

		response.setStatus(HttpServletResponse.SC_OK);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("company : " + request.getParameter("company_name"));
		System.out.println("password : " + request.getParameter("password_company"));

		LoginForm form = new LoginForm(companyDao);

		Company company = form.loginCompany(request);

		if (form.getErrors().isEmpty()) {

			response.setStatus(HttpServletResponse.SC_OK);
			System.out.println("OK");
		} else {

			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			System.out.println("refusé");
		}

	}

}