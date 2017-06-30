package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Company;
import beans.FileUpload;
import dao.DAOFactory;
import dao.FileUploadDao;

@SuppressWarnings("serial")
@WebServlet("/homepage")
public class Homepage extends HttpServlet {

	// TODO Constantes
	public static final String	CONF_DAO_FACTORY	= "daofactory";

	public static final String	ATT_COMPANY_SESSION	= "companySession";

	public static final String	ATT_CLIENT			= "client";
	public static final String	ATT_FORM			= "form";

	public static final String	VUE					= "/WEB-INF/homepage.jsp";

	public static final String	URL_REDIRECTION		= "/login";
	private static final String	ATT_FILES_SESSION	= "files";

	private FileUploadDao		fileUploadDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.fileUploadDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFileUploadDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession httpSession = request.getSession();

		Company company = (Company) httpSession.getAttribute(ATT_COMPANY_SESSION);

		if (company == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + URL_REDIRECTION);
		} else {

			List<FileUpload> filesUpload = fileUploadDao.findAllByCompany(company.getId());
			httpSession.setAttribute(ATT_FILES_SESSION, filesUpload);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}

	}
}