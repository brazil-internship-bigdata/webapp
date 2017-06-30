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

@SuppressWarnings("serial")
@WebServlet("/download")
@MultipartConfig(location = "/temp", maxFileSize = 10 * 1024 * 1024, maxRequestSize = 5 * 10 * 1024
		* 1024, fileSizeThreshold = 1024 * 1024)
public class Download extends HttpServlet implements ServletConstants {

	private CompanyDao companyDao;

	public void init() throws ServletException {
		this.companyDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCompanyDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String file = req.getParameter(API_PARAM_FILE);
		String companyName = req.getParameter(API_PARAM_COMPANY);
		String password = req.getParameter(API_PARAM_PASSWORD);

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