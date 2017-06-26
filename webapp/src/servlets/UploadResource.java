package servlets;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 * @author Michal Gajdos
 */
@Path("upload")
public class UploadResource {

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

		if (storeFile(APIApplication.CSV_FILE_PATH + filename, data))
			return "CSV File saved";
		else
			return "Error during saving csv file";
	}

	@POST
	@Path("ktr")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String postKTR(@FormDataParam("file") String data, @FormDataParam("file") FormDataContentDisposition d) {
		if (storeFile(APIApplication.KTR_FILE_PATH + d.getFileName(), data))
			return "KTR File saved";
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