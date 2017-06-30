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
import dao.FileUploadDao;
import forms.LoginForm;

@SuppressWarnings("serial")
@WebServlet("/login")
public class Login extends HttpServlet {
	// TODO Constantes
	public static final String	CONF_DAO_FACTORY	= "daofactory";
	public static final String	HOMEPAGE			= "homepage";
	public static final String	ATT_COMPANY			= "company";
	public static final String	ATT_FORM			= "form";
	public static final String	ATT_COMPANY_SESSION	= "companySession";
	public static final String	VUE					= "/WEB-INF/login.jsp";
	public static final String	ACCES_RESTREINT		= "/homepage";

	private CompanyDao			companyDao;
	private FileUploadDao		fileUploadDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.companyDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCompanyDao();
		this.fileUploadDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFileUploadDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		LoginForm form = new LoginForm(companyDao);

		/* Traitement de la requête et récupération du bean en résultant */
		Company company = form.loginCompany(request);

		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		/**
		 * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
		 * Utilisateur à la session, sinon suppression du bean de la session.
		 */
		if (form.getErrors().isEmpty()) {
			session.setAttribute(ATT_COMPANY_SESSION, company);

			System.out.println(company.getCompanyName());

			response.sendRedirect(HOMEPAGE);
		} else {
			session.setAttribute(ATT_COMPANY_SESSION, null);

			/* Stockage du formulaire et du bean dans l'objet request */
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_COMPANY, company);

			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}

	}
}