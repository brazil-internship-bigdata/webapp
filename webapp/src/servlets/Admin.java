package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Company;
import dao.CompanyDao;
import dao.DAOFactory;

@WebServlet("/admin")
public class Admin extends ServletWithConstants {

	private CompanyDao companyDao;

	public void init() throws ServletException {
		this.companyDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCompanyDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();

		List<Company> companies = companyDao.companyOverview();

		httpSession.setAttribute(ATT_COMPANIES_SESSION, companies);
		httpSession.setAttribute(ATT_NB_COMPANY, companies.size());

		this.getServletContext().getRequestDispatcher(ADMIN_VIEW).forward(request, response);
	}

}
