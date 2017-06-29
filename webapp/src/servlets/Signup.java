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
public class Signup extends HttpServlet {
	public static final String	CONF_DAO_FACTORY	= "daofactory";
	public static final String	ATT_COMPANY			= "company";
	public static final String	ATT_FORM			= "form";
	public static final String	VIEW				= "/WEB-INF/signup.jsp";

	private CompanyDao			companyDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.companyDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCompanyDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		SignUpForm form = new SignUpForm(companyDao);

		/* Traitement de la requête et récupération du bean en résultant */
		Company company = form.signUpCompany(request);

		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);

		if (!form.getErrors().isEmpty()) {
			request.setAttribute(ATT_COMPANY, company);
		} else {
			request.setAttribute(ATT_COMPANY, null);
		}
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);

	}

}