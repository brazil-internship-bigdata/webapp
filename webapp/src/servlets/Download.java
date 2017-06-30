package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CompanyDao;
import dao.DAOFactory;
import dao.FileUploadDao;

@WebServlet("/download")
@MultipartConfig(location = "/temp", maxFileSize = 10 * 1024 * 1024, maxRequestSize = 5 * 10 * 1024
		* 1024, fileSizeThreshold = 1024 * 1024)
public class Download extends HttpServlet {

	// TODO Constantes
	public static final String	CONF_DAO_FACTORY	= "daofactory";
	public static final String	STORAGE_FILE_PATH	= "/home/geourjoa/git/webapp/webapp/storage/";
	private static final int	TAILLE_TAMPON		= 200;
	private static final String	RESOURCE_FILE_PATH	= "/resource/";
	private static final String	DATA_FILE_PATH		= "/data/";

	private CompanyDao			companyDao;
	private FileUploadDao		fileUploadDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.companyDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCompanyDao();
		this.fileUploadDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFileUploadDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Constantes
		String file = req.getParameter("file");
		String companyName = req.getParameter("company");
		String password = req.getParameter("password");

		if (companyDao.loginCheck(companyName, password)) {
			String filePath = STORAGE_FILE_PATH + companyName + RESOURCE_FILE_PATH;

			OutputStream out = resp.getOutputStream();
			FileInputStream in = new FileInputStream(new File(filePath + file));
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.flush();

			resp.setStatus(HttpServletResponse.SC_OK);
		} else {
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}

	}
}