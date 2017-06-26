package servlets;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import beans.FileUpload;
import dao.DAOFactory;
import dao.FileUploadDao;

/**
 * @author Michal Gajdos
 */
@Path("upload")
public class UploadResource {

	public static final String	CONF_DAO_FACTORY	= "daofactory";

	private FileUploadDao		fileUploadDAO;

	@Context
	ServletContext				contexte;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.fileUploadDAO = ((DAOFactory) contexte.getAttribute(CONF_DAO_FACTORY)).getFileUploadDao();
	}

	@GET
	@Produces("text/plain")
	public String getHello() {
		return "Hello World! (GET)";
	}

	// Upload a csv on the server
	@POST
	@Path("csv")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String postCSV(@FormDataParam("file") String data, @FormDataParam("file") FormDataContentDisposition d) {

		String filename = d.getFileName();

		if (!filename.substring(filename.lastIndexOf('.')).equalsIgnoreCase(".csv")) {
			return "Error, is not a CSV file";
		}

		// Add date at the name of the file
		filename = filename.substring(0, filename.lastIndexOf('.'));

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(APIApplication.DATE_FORMAT);

		filename += dateFormat.format(date) + ".csv";

		FileUpload fileUpload = new FileUpload();
		fileUpload.setDateUpload(new Timestamp(new Date().getTime()));
		fileUpload.setFilename(filename);
		fileUpload.setFileType(".csv");
		fileUpload.setSizeFile((long) data.length());

		if (storeFile(APIApplication.CSV_FILE_PATH + filename, data)) {
			fileUploadDAO.create(fileUpload);
			return "CSV File saved";
		}

		else
			return "Error during saving csv file";
	}

	@POST
	@Path("ktr")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String postKTR(@FormDataParam("file") String data, @FormDataParam("file") FormDataContentDisposition d) {
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