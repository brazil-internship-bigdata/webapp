package servlets;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import beans.FileUpload;
import dao.CompanyDao;
import dao.DAOFactory;
import dao.FileUploadDao;

/**
 * @author Michal Gajdos
 */
@Path("upload")
public class UploadResource {

	public static final String	CONF_DAO_FACTORY	= "daofactory";

	private FileUploadDao		fileUploadDAO;
	private CompanyDao			companyDao;

	@Context
	ServletContext				context;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.fileUploadDAO = ((DAOFactory) context.getAttribute(CONF_DAO_FACTORY)).getFileUploadDao();
		this.companyDao = ((DAOFactory) context.getAttribute(CONF_DAO_FACTORY)).getCompanyDao();
	}

	@GET
	@Produces("text/plain")
	public String getHello(@QueryParam("test") String t) {
		System.out.println(t);

		return "Hello World! (GET)";

	}

	// Upload a csv on the server
	@POST
	@Path("csv")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String postCSV(@FormDataParam("file") String data, @FormDataParam("file") FormDataContentDisposition d,
			@FormParam("company") String company, @FormParam("password") String password) {

		if (companyDao.loginCheck(company, password)) {
			String filename = d.getFileName();

			if (!filename.substring(filename.lastIndexOf('.')).equalsIgnoreCase(".csv")) {
				return "Error, is not a CSV file";
			}

			// Add date at the name of the file
			filename = filename.substring(0, filename.lastIndexOf('.'));

			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat(APIApplication.DATE_FORMAT);

			filename += dateFormat.format(date) + ".csv";

			if (storeFile(APIApplication.CSV_FILE_PATH + filename, data)) {
				FileUpload fileUpload = new FileUpload();
				fileUpload.setDateUpload(new Timestamp(new Date().getTime()));
				fileUpload.setFilename(filename);
				fileUpload.setFileType(".csv");
				fileUpload.setSizeFile((long) data.length());

				fileUploadDAO.create(fileUpload);
				return "CSV File saved";
			}

			else
				return "Error during saving csv file";
		} else {
			return "Authentification failed";
		}

	}

	@POST
	@Path("ktr")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String postKTR(@FormDataParam("file") String data, @FormDataParam("file") FormDataContentDisposition d,
			@HeaderParam("company") String company, @HeaderParam("password") String password) {

		if (companyDao.loginCheck(company, password)) {

			if (storeFile(APIApplication.KTR_FILE_PATH + d.getFileName(), data)) {

				FileUpload fileUpload = new FileUpload();
				fileUpload.setDateUpload(new Timestamp(new Date().getTime()));
				fileUpload.setFilename(d.getFileName());
				fileUpload.setFileType(".ktr");
				fileUpload.setSizeFile((long) data.length());
				return "KTR File saved";
			}

			else
				return "Error during saving ktr file";
		} else {
			return "Authentification failed";
		}

	}

	private boolean storeFile(String fileName, String data) {
		try {
			PrintWriter writer = new PrintWriter(fileName);
			writer.println(data);
			writer.close();
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}