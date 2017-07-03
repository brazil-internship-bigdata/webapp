package servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Company;
import beans.FileUpload;
import dao.DAOFactory;
import dao.FileUploadDao;

//TODO changer mot de passe, info etc ..

@SuppressWarnings("serial")
@WebServlet("/homepage")
public class Homepage extends ServletWithConstants {

	private FileUploadDao fileUploadDao;

	public void init() throws ServletException {
		this.fileUploadDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFileUploadDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession httpSession = request.getSession();

		Company company = (Company) httpSession.getAttribute(ATT_COMPANY_SESSION);

		if (company == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + URL_REDIRECTION);
		} else {

			List<FileUpload> filesDataUpload = fileUploadDao.findAllDataByCompany(company.getId());
			httpSession.setAttribute(ATT_FILES_DATA_SESSION, filesDataUpload);

			List<FileUpload> filesResourceUpload = fileUploadDao.findAllResourceByCompany(company.getId());
			httpSession.setAttribute(ATT_FILES_RESOURCE_SESSION, filesResourceUpload);

			int quantityData = fileUploadDao.quantityOfData(company.getId());
			httpSession.setAttribute(ATT_QUANTITY, readableFileSize(quantityData));

			int numberDataFile = fileUploadDao.numbertOfDataFile(company.getId());
			httpSession.setAttribute(ATT_NUMBER_DATA_FILE, numberDataFile);

			int numberResourceFile = fileUploadDao.numbertOfDataFile(company.getId());
			httpSession.setAttribute(ATT_NUMBER_RESOURCE_FILE, numberResourceFile);

			this.getServletContext().getRequestDispatcher(HOMEPAGE_VIEW).forward(request, response);
		}

	}

	private static String readableFileSize(long size) {
		if (size <= 0)
			return "0";
		final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
}