package servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import beans.Company;
import beans.FileUpload;
import dao.CompanyDao;
import dao.DAOException;
import dao.DAOFactory;
import dao.FileUploadDao;

@SuppressWarnings("serial")
@WebServlet("/upload")
@MultipartConfig(location = "/temp", maxFileSize = 10 * 1024 * 1024, maxRequestSize = 5 * 10 * 1024
		* 1024, fileSizeThreshold = 1024 * 1024)
public class Upload extends ServletWithConstants {

	private CompanyDao		companyDao;
	private FileUploadDao	fileUploadDao;

	public void init() throws ServletException {
		this.companyDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCompanyDao();
		this.fileUploadDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFileUploadDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String fichier = req.getParameter(API_PARAM_FILE);
		String companyName = req.getParameter(API_PARAM_COMPANY);
		String password = req.getParameter(API_PARAM_PASSWORD);

		String filename = req.getParameter("fileName");
		String extension = filename.substring(filename.lastIndexOf('.') + 1, filename.length());

		if (companyDao.loginCheck(companyName, password)) {

			try {
				String path;
				if (extension.equals(DATA_FILE_EXTENSION)) {
					path = STORAGE_FILE_PATH + companyName + DATA_FILE_PATH;
				} else {
					path = STORAGE_FILE_PATH + companyName + RESOURCE_FILE_PATH;
				}

				PrintWriter writer = new PrintWriter(path + filename);
				writer.println(fichier);
				writer.close();

			} catch (IOException e) {
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}

			FileUpload fileUpload = new FileUpload();
			fileUpload.setDateUpload(new Timestamp(new Date().getTime()));
			fileUpload.setFilename(filename);

			fileUpload.setFileType(extension);
			fileUpload.setSizeFile((long) fichier.length());

			Company company = companyDao.find(companyName);

			fileUpload.setIdComapny(company.getId());

			try {
				fileUploadDao.create(fileUpload);
			} catch (DAOException e) {
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}

			resp.setStatus(HttpServletResponse.SC_OK);

		} else {
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}

	}

	private void writeFile(Part part, String nomFichier, String chemin) throws IOException {
		/* Prépare les flux. */
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			/* Ouvre les flux. */
			entree = new BufferedInputStream(part.getInputStream(), BUFFER_SIZE_UPLOAD);
			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), BUFFER_SIZE_UPLOAD);

			byte[] tampon = new byte[BUFFER_SIZE_UPLOAD];
			int longueur;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}

		} finally {
			try {
				sortie.close();
			} catch (IOException ignore) {
			}
			try {
				entree.close();
			} catch (IOException ignore) {
			}
		}
	}

}
