package servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.Company;
import beans.FileUpload;
import dao.CompanyDao;
import dao.DAOException;
import dao.DAOFactory;
import dao.FileUploadDao;

@SuppressWarnings("serial")
@WebServlet("/uploadResource")
@MultipartConfig(location = "/temp", maxFileSize = 10 * 1024 * 1024, maxRequestSize = 5 * 10 * 1024
		* 1024, fileSizeThreshold = 1024 * 1024)
public class UploadResource extends ServletWithConstants {

	private CompanyDao		companyDao;
	private FileUploadDao	fileUploadDao;

	public void init() throws ServletException {
		this.companyDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCompanyDao();
		this.fileUploadDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFileUploadDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Company> companies = companyDao.companyOverview();
		HttpSession session = request.getSession();
		session.setAttribute("companyList", companies);

		this.getServletContext().getRequestDispatcher(UPLOAD_RESOURCE_VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Part part = req.getPart("file");
		String companyName = req.getParameter("companyList");

		String filename = part.getSubmittedFileName();

		String extension = filename.substring(filename.lastIndexOf('.') + 1, filename.length());

		try {
			String path;
			if (extension.equals(DATA_FILE_EXTENSION)) {
				path = STORAGE_FILE_PATH + companyName + DATA_FILE_PATH;
			} else {
				path = STORAGE_FILE_PATH + companyName + RESOURCE_FILE_PATH;
			}
			writeFile(part, filename, path);
		} catch (IOException e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		FileUpload fileUpload = new FileUpload();
		fileUpload.setDateUpload(new Timestamp(new Date().getTime()));
		fileUpload.setFilename(filename);

		fileUpload.setFileType(extension);
		fileUpload.setSizeFile((Long) part.getSize());

		Company company = companyDao.find(companyName);

		fileUpload.setIdComapny(company.getId());

		try {
			fileUploadDao.create(fileUpload);
		} catch (DAOException e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		resp.setStatus(HttpServletResponse.SC_OK);

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
